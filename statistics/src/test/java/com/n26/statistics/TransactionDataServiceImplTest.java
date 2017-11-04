package com.n26.statistics;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.statistics.data.TransactionResponse;
import com.n26.statistics.service.TransactionDataServiceImpl;
import com.n26.statistics.util.CommonUtil;
import com.n26.statistics.validation.RequestValidation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { StatisticsApplication.class })
@TestPropertySource(locations = {"classpath:application.properties"},properties = {"config.time=60000"})
public class TransactionDataServiceImplTest {

	private Map<Long, Double> transMap;

	@Mock
	private CommonUtil commonUtil;

	@InjectMocks
	TransactionDataServiceImpl transactionDataServiceImpl;

	@Mock
	RequestValidation validation;

	MockHttpServletRequest mockRequest;

	MockHttpServletResponse mockResponse;

	@Before
	public void setUp() {

		// request = new TransactionRequest();
		transMap = new TreeMap<>();
		commonUtil = mock(CommonUtil.class);
		//transactionDataServiceImpl = mock(TransactionDataServiceImpl.class);
	}

	
	@Test
	public void testGetTransactionStatistics() throws JsonProcessingException {

		ObjectMapper objMapper = new ObjectMapper();

		transactionDataServiceImpl.setConfigTime(60000);
		
		when(commonUtil.getObjectMapper()).thenReturn(objMapper);

		transMap.put(System.currentTimeMillis()-20000, 42.00);

		String statistics = transactionDataServiceImpl.getTransactionStatistics(transMap, System.currentTimeMillis());

		TransactionResponse response = new TransactionResponse();
		response.setSum(42.00);
		response.setAvg(42.00);
		response.setMax(42.00);
		response.setMin(42.00);
		response.setCount(1);

		assertEquals(objMapper.writeValueAsString(response), statistics);

	}

}
