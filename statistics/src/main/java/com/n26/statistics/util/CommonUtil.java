package com.n26.statistics.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This class will contain all the utility methods used in the project.
 * 
 * @author Ajay
 *
 */

@Component
public class CommonUtil {


	@Autowired
	private ObjectMapper objectMapper;

	
	/**
	 * 
	 * @return current time in millis.
	 */
	public long getCurrentTimeInMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * @return the objectMapper
	 */
	public ObjectMapper getObjectMapper() {
		return objectMapper;
	}
	
	
}
