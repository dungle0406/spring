package spring.test.mentor.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Order(0)
@Aspect
@Configuration
@Slf4j
@Getter
public class LogAspect {
    @Getter
    private static List<LogMessage> logMessages;

    public LogAspect() {
        logMessages = new ArrayList<>();
    }

    @Around(value = "spring.test.mentor.aop.Pointcuts.mainPointCut()")
    public Object calculateMethodTimeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        if (log.isDebugEnabled()) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

        String path = request.getRequestURI();
        log.debug(path);

        String method = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
        log.debug(method);
        Instant time = Instant.now();
        int responseStatus;

        if (response != null) {
            responseStatus = response.getStatus();
        } else {
            responseStatus = -1;
        }

        LogMessage logMessage = LogMessage
                .builder()
                .path(path)
                .method(method)
                .time(time)
                .responseStatusCode(responseStatus)
                .build();

        log.debug(logMessage.toString());
        logMessages.add(logMessage);

        return joinPoint.proceed();
    }
}
