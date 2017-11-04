package com.n26.statistics.service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.n26.statistics.data.TransactionRequest;

/**
 * Service methods to save transaction and get statistics of transactions.
 * 
 * @author Ajay
 *
 */

public interface TransactionDataService {

	/**
	 * This method stores the transactions in Map.
	 * 
	 * @param request
	 * @param transactionMap
	 * @param currentTimeInMillis
	 * @return
	 */
	public boolean saveTransaction(TransactionRequest request,Map<Long,Double> transactionMap,long currentTimeInMillis);
	
	/**
	 * This method generates statistics for all the transactions happened in last 60 seconds.
	 * 
	 * @param transactionMap
	 * @param currentTimeInMillis
	 * @return
	 * @throws JsonProcessingException
	 */
	public String getTransactionStatistics(Map<Long,Double> transactionMap, long currentTimeInMillis) throws JsonProcessingException;
	
}
