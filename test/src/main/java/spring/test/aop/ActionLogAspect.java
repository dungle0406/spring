package spring.test.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;

@Aspect
@Component
@Slf4j
public class ActionLogAspect {
    private final ActionLogService actionLogService;

    @Autowired
    public ActionLogAspect(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }
    @AfterThrowing(value = "execution(* spring.test.mentor..*.*(..))", throwing = "ex")
    public Object handleExceptions(Throwable ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof HttpClientErrorException.MethodNotAllowed || ex instanceof HttpRequestMethodNotSupportedException) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
        } else if (ex instanceof HttpMessageNotReadableException || ex instanceof MissingServletRequestParameterException) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        ActionLog actionLog = handleException(request, ex, httpStatus);
        log.debug(actionLog.toString());
        actionLogService.addNewActionLog(actionLog);
        return actionLog;
    }


    private ActionLog handleException(HttpServletRequest request, Throwable ex, HttpStatus httpStatus) {
        return ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(httpStatus.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();
    }
}
