package com.foodOrder.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all methods in controller package
    @Pointcut("execution(* com.foodOrder.controller..*(..))")
    public void controllerMethods() {
    }

    // Before advice
    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Entering method: {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    // After returning advice
    @AfterReturning(pointcut = "controllerMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
    }
}
