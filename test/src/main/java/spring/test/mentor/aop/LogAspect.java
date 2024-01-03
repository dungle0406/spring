//package spring.test.mentor.aop;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.aopalliance.intercept.Joinpoint;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.time.Instant;
//
//@Aspect
//@Component
//@Slf4j
//public class LogAspect {
//    @Pointcut(value = "execution(* spring.test.mentor.service.MentorService.*(..))")
//    private void printLogs() {
//    }
//
//    @Before("execution(* spring.test.mentor.service.MentorService.*(..))")
//    public void printLogsStatementsBefore() {
//        System.out.println("If there is no @Around, @Before will be called first!");
//    }
//
//    @After("execution(* spring.test.mentor.service.MentorService.*(..))")
//    public void printLogsStatementsAfter() {
//        System.out.println("If there is no @Around, @After will be called after @Before(if available!)!");
//    }
//
//    @AfterReturning(value = "execution(* spring.test.mentor.service.MentorService.*(..))", returning = "mentors")
//    public void logAfterRunningDisplay(JoinPoint joinpoint, Object mentors) {
//        System.out.println("After Returning method:" + joinpoint.getSignature());
//    }
//
//    @AfterThrowing(value = "execution(* spring.test.mentor.service.MentorService.*(..))", throwing = "ex")
//    public void
//    logsAfterThrowingDisplay(JoinPoint jPoint, Exception ex) {
//        System.out.println("After Throwing exception in method:" + jPoint.getSignature());
//        System.out.println("Exception is:" + ex.getMessage());
//    }
//
//    @Around(value = "printLogs()")
//    public void logsAroundAdvice(ProceedingJoinPoint proJoinPoint) throws Throwable {
//        {
//            System.out.println(
//                    "The method aroundAdvice() before invocation of the method "
//                            + proJoinPoint.getSignature().getName()
//                            + " method");
//            try {
//                proJoinPoint.proceed();
//            } finally {
//            }
//            System.out.println(
//                    "The method aroundAdvice() after invocation of the method "
//                            + proJoinPoint.getSignature().getName()
//                            + " method");
//        }
//    }
//}
