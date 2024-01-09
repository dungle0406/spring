package spring.test.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.test.mentor.error.InvalidRatingBadRequest;
import spring.test.mentor.error.LackOfInformation;
import spring.test.mentor.error.MentorNotFound;

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

    @AfterThrowing(value = "execution(@spring.test.advice.ActionLogDetails * *(..))", throwing = "ex")
    public Object handleExceptions(Throwable ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof InvalidRatingBadRequest || ex instanceof LackOfInformation) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof MentorNotFound) {
            httpStatus = HttpStatus.NOT_FOUND;
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