package spring.test.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ActionLogAspect {
    private final ActionLogService actionLogService;

    @Autowired
    public ActionLogAspect(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }

    @AfterThrowing
    
}
