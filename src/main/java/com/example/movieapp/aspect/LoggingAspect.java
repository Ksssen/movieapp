package com.example.movieapp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger;

    @Autowired
    public LoggingAspect(Logger logger) {
        this.logger = logger;
    }

    @Before("execution(* com.example.movieapp.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Invoking method: " + joinPoint.getSignature().getName());
    }
}
