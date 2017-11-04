package com.n26.statistics.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.n26.statistics.data.TransactionRequest;
import com.n26.statistics.exception.ExceptionHandlerAdvice;


/**
 * 
 * This class is used to validate input.
 * 
 * @author Ajay
 *
 */
@Component
public class RequestValidation {

	private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	public boolean validate(TransactionRequest request) {

		boolean result;
		if (request.getAmount() < 0 || request.getTimestamp() < 0) {
			logger.debug("Validation failed for input"+request);
			result = false;
		} else {
			result = true;
		}
		return result;
	}

}
