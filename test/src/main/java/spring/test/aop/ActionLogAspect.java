package spring.test.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import spring.test.mentor.error.InvalidRatingBadRequest;
import spring.test.mentor.error.LackOfInformation;

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

    @Pointcut(value = "execution(* spring.test.mentor.service.MentorService.*(..))")
    public void printLogs() {
    }

    @Before("execution(* spring.test.mentor.service.MentorService.*(..))")
    public void printLogsStatementsBefore() {
        System.out.println("If there is no @Around, @Before will be called first!");
    }

    @After("execution(* spring.test.mentor.service.MentorService.*(..))")
    public void printLogsStatementsAfter() {
        System.out.println("If there is no @Around, @After will be called after @Before(if available!)!");
    }

    @AfterReturning(value = "execution(* spring.test.mentor.service.MentorService.*(..))", returning = "mentors")
    public void logAfterRunningDisplay(JoinPoint joinpoint, Object mentors) {
        System.out.println("After Returning method:" + joinpoint.getSignature());
    }

    @AfterThrowing(value = "execution(* spring.test.mentor.service.MentorService.*(..))", throwing = "ex")
    public Object handleExceptions(Throwable ex) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        if(!(ex instanceof UnsupportedOperationException)) {
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
