package spring.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final  LogService logService;

    @Autowired
    public LoggingAspect(LogService logService) {
        this.logService = logService;
    }

    @Around("@annotation(Loggable)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable{
        String path = extractPath(joinPoint);
        String method = extractMethod(joinPoint);

        try {
            Object result = joinPoint.proceed();
            logService.addLog(new LogMessage(path,method,200L));
            return result;
        } catch (Exception e) {
            logService.addLog(new LogMessage(path, method, 404L));
            throw e;
        }
    }

    private String extractPath(ProceedingJoinPoint joinPoint) {
        return "/api/mentors";
    }

    private String extractMethod(ProceedingJoinPoint joinPoint) {
        return "GET";
    }
}
