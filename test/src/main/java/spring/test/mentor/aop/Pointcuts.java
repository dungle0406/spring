package spring.test.mentor.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointcut() {
    }
    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void servicePointcut() {
    }
    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryPointcut() {
    }
    @Pointcut("execution(* spring.test..*(..))")
    public void appPointcut() {
    }
    @Pointcut("appPointcut() && controllerPointcut() || servicePointcut() || repositoryPointcut()")
    public void mainPointCut() {
    }
}
