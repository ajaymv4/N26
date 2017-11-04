package com.n26.statistics.service;

import java.util.DoubleSummaryStatistics;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.statistics.data.TransactionRequest;
import com.n26.statistics.data.TransactionResponse;
import com.n26.statistics.util.CommonUtil;

@Component
public class TransactionDataServiceImpl implements TransactionDataService {

	@Autowired
	CommonUtil commonUtil;
	
	@Value("${config.time}")
	private long configTime;

	private final Logger logger = LoggerFactory.getLogger(TransactionDataServiceImpl.class);
	
	private ObjectMapper objMapper = new ObjectMapper();

	
	/**
	 * This method saves the transaction in a Map.
	 */
	@Override
	public boolean saveTransaction(TransactionRequest request, Map<Long, Double> transactionMap,
			long currentTimeInMillis) {

		transactionMap.put(request.getTimestamp(), request.getAmount());

		long timediff = currentTimeInMillis - request.getTimestamp();

		logger.debug("Time diff ==>" + timediff);

		if (timediff >= configTime) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method Generate statistics.
	 */
	@Override
	public String getTransactionStatistics(Map<Long, Double> transactionMap, long currentTimeInMillis)
			throws JsonProcessingException {

		
		long fromTime = currentTimeInMillis - configTime;
		
		logger.debug("Contents of Map are "+transactionMap);
		
		logger.debug("fromTime==>"+fromTime+" CurrentTime==>"+currentTimeInMillis);
		
		SortedMap<Long, Double> newMap = ((TreeMap<Long, Double>) transactionMap).subMap(fromTime,
				currentTimeInMillis);

		TransactionResponse response = new TransactionResponse();

		if(!newMap.isEmpty()) {
			DoubleSummaryStatistics stats = newMap.values().stream()
					.collect(Collectors.summarizingDouble(Double::doubleValue));
			response.setSum(stats.getSum());
			response.setAvg(stats.getAverage());
			response.setMax(stats.getMax());
			response.setMin(stats.getMin());
			response.setCount(stats.getCount());
		}
		
		return objMapper.writeValueAsString(response);
	}

	/**
	 * @return the configTime
	 */
	public long getConfigTime() {
		return configTime;
	}

	/**
	 * @param configTime the configTime to set
	 */
	public void setConfigTime(long configTime) {
		this.configTime = configTime;
	}

	
	
	
}
