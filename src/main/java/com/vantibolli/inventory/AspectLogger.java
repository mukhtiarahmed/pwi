/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * The AOP component to perform logging.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Component
@Aspect
public class AspectLogger {

    /**
     * The logger class name.
     */
    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class.getName());
   
    /**
     * Log method entrance.
     *
     * @param joinPoint the joint point
     */
    @Before("execution(* *(..)) && @annotation(com.vantibolli.inventory.annotations.LogMethod)")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        String[] parameterNames = ((MethodSignature) (joinPoint.getSignature())).getParameterNames();
        if (parameterNames == null) {
            parameterNames = new String[joinPoint.getArgs().length];
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                parameterNames[i] = "arg" + i;
            }
        }
        InventoryHelper.logEntrance(LOGGER, joinPoint.getSignature().toString(),
                parameterNames, joinPoint.getArgs());
    }

    /**
     * Log method exit.
     *
     * @param joinPoint the join point
     * @param result the result
     */
    @AfterReturning(
            pointcut = "execution(* *(..)) && @annotation(com.vantibolli.inventory.annotations.LogMethod)",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
    	InventoryHelper.logExit(LOGGER, joinPoint.getSignature().toString(), result);
    }

    /**
     * Log exception.
     *
     * @param joinPoint the joint point
     * @param ex the exception
     */
    @AfterThrowing(
            pointcut = "execution(* *(..)) && @annotation(com.vantibolli.inventory.annotations.LogMethod)",
            throwing = "ex")
    public void doRecoveryActions(JoinPoint joinPoint, Exception ex) {
    	InventoryHelper.logException(LOGGER, joinPoint.getSignature().toString(), ex);
    }
}
