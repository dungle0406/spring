package spring.test.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.HttpEntityMethodProcessor;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

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

    @AfterThrowing(value = "execution(* spring.test.mentor.controller..*.*(..))", throwing = "ex")
    public void handleExceptions(JoinPoint joinPoint, Throwable ex) {
        log.debug("Exception caught: " + ex.toString());

        log.debug("Method Signature: " + joinPoint.getSignature());
        log.debug("Exception Message: " + ex.getMessage());

        ActionLog logMessage = createLogMessage(joinPoint, ex);
        actionLogService.addNewActionLog(logMessage);
    }

    private ActionLog createLogMessage(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();

        String logMessage = "Exception in method " + className + "." + methodName;


        return ActionLog.builder()
                .path(className)
                .method(methodName)
                .time(Instant.now())
                .responseStatusCode(500)
                .message(logMessage)
                .build();    }
}
