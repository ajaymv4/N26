package com.n26.statistics.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.statistics.constants.AppConstants;
import com.n26.statistics.constants.StatusCodes;
import com.n26.statistics.data.TransactionRequest;
import com.n26.statistics.service.TransactionDataService;
import com.n26.statistics.util.CommonUtil;
import com.n26.statistics.util.LogExecutionTime;
import com.n26.statistics.validation.RequestValidation;


/**
 * Main Controller of the Application.
 * @author 
 *
 */

@RestController
@RequestMapping(AppConstants.N26)
public class TransactionController {

	private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionDataService transactionDataService;

	@Autowired
	CommonUtil commonUtil;

	@Autowired
	RequestValidation validation;

	private Map<Long, Double> transactionMap = new TreeMap<>();

	/**
	 * Test method.
	 * @return
	 */
	@RequestMapping(value = "/currenttime", method = RequestMethod.GET)
	@LogExecutionTime
	public long currentTimeInMillis() {
		return commonUtil.getCurrentTimeInMillis();
	}

	/**
	 * This method saves transactions.
	 * 
	 * status code 201 means transaction happened in last 60 seconds.
	 * status code 204 means transactions older than 60 seconds.
	 * 
	 * @param tranRequest
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = AppConstants.TRANSATIONS_RESOURCE_PATH, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@LogExecutionTime
	public @ResponseBody void saveTransaction(@RequestBody TransactionRequest tranRequest, HttpServletRequest request,
			HttpServletResponse response) {

		long time = commonUtil.getCurrentTimeInMillis();

		if (validation.validate(tranRequest)) {

			boolean isOlderThan60 = transactionDataService.saveTransaction(tranRequest, transactionMap, time);
			logger.debug("CurrentTime==>" + time);
			if (isOlderThan60) {
				response.setStatus(StatusCodes.TWO_ZERO_FOUR.getValue());
			} else {
				response.setStatus(StatusCodes.TWO_ZERO_ONE.getValue());
			}
		} else {
			response.setStatus(HttpStatus.BAD_REQUEST.value());

		}

	}

	/**
	 * This method generates statistics for the transactions happened in last 60 seconds. 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonProcessingException
	 */
	
	@RequestMapping(value = AppConstants.STATISTICS_RESOURCE_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@LogExecutionTime
	public @ResponseBody String generateStatistics(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		String statistics = null;

		statistics = transactionDataService.getTransactionStatistics(transactionMap,
				commonUtil.getCurrentTimeInMillis());

		return statistics;

	}

}
