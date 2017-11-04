package com.n26.statistics.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.n26.statistics.controller.TransactionController;

/**
 * Aspect to Log execution time of requests.
 * @author Ajay
 *
 */

@Aspect
@Component
public class LogginAspect {

	
	private final Logger logger = LoggerFactory.getLogger(LogginAspect.class);
	
	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

		
		long start = System.currentTimeMillis();
		 
	    Object proceed = joinPoint.proceed();
	 
	    long executionTime = System.currentTimeMillis() - start;
	 
	    logger.debug(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    
	    return proceed;
	
	}
	
}
