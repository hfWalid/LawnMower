package org.mowitnow.lawnmower.application;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfig {
    private static final Logger logger = LoggerFactory.getLogger(LoggingConfig.class);

    @Pointcut("execution(* org.mowitnow.lawnmower.infrastructuree.*.*(..))")
    public void adaptersPointCut() {}

    @Pointcut("execution(* org.mowitnow.lawnmower.domain.servicee.*.*(..))")
    public void servicePointCut() {}

    @Before("servicePointCut() || adaptersPointCut()")
    public void logBeforeServiceExecution(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        StringBuilder logMessage = new StringBuilder("Before executing service method: ");
        logMessage.append(methodSignature.toShortString());
        logMessage.append(" with arguments: [");

        StringBuilder openBracket = new StringBuilder("{");
        for (int i = 0; i < parameterNames.length; i++) {
            logMessage.append(openBracket.append(parameterNames[i]).append(" = ").append(args[i]).append("}"));
            if (i < parameterNames.length - 1) {
                logMessage.append(", ");
            }
        }
        logMessage.append("]");
        System.out.println(logMessage);
        logger.info(logMessage.toString());
    }

    @After("servicePointCut() || adaptersPointCut()")
    public void logAfterServiceExecution(JoinPoint joinPoint) {
        System.out.println("After executing service method: " + joinPoint.getSignature().toShortString());
        logger.info("After executing service method: {}", joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "servicePointCut() || adaptersPointCut()", returning = "result")
    public void logAfterServiceReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After returning from service method: " + joinPoint.getSignature().toShortString() + ". Returned: {}" + result);
        logger.info("After returning from service method: {}. Returned: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(pointcut = "servicePointCut() || adaptersPointCut()", throwing = "exception")
    public void logAfterServiceThrowing(JoinPoint joinPoint, Exception exception) {
        System.out.println("Exception thrown from service method: " + joinPoint.getSignature().toShortString() + ". EException: " + exception.getMessage());
        logger.error("Exception thrown from service method: {}. Exception: {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}
