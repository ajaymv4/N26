package com.n26.statistics;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.reset;
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

import com.n26.statistics.constants.StatusCodes;
import com.n26.statistics.controller.TransactionController;
import com.n26.statistics.data.TransactionRequest;
import com.n26.statistics.service.TransactionDataServiceImpl;
import com.n26.statistics.util.CommonUtil;
import com.n26.statistics.validation.RequestValidation;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { StatisticsApplication.class })
@TestPropertySource(locations = "classpath:application.properties")
public class TransactionControllerTest {

	@InjectMocks
	private TransactionController controller;

	@Mock
	private TransactionDataServiceImpl transactionDataServiceImpl;

	private Map<Long, Double> transMap;

	@Mock
	private CommonUtil commonUtil;

	@Mock
	RequestValidation validation;

	MockHttpServletRequest mockRequest;

	MockHttpServletResponse mockResponse;

	@Before
	public void setUp() {

		reset(transactionDataServiceImpl);
		reset(commonUtil);
		//request = new TransactionRequest();
		transMap = new TreeMap<>();
		
		mockRequest = new MockHttpServletRequest();
		mockResponse = new MockHttpServletResponse();
	}

	@Test
	public void testSaveTransNotOlderThanSixtySeconds() {

		reset(transactionDataServiceImpl);
		TransactionRequest request = new TransactionRequest();
		request.setAmount(42.00);
		request.setTimestamp(System.currentTimeMillis());
		
		when(transactionDataServiceImpl.saveTransaction(request, transMap, System.currentTimeMillis())).thenReturn(false);
		when((commonUtil.getCurrentTimeInMillis())).thenReturn(System.currentTimeMillis());
		when(validation.validate(request)).thenReturn(true);

		controller.saveTransaction(request, mockRequest, mockResponse);
		assertEquals(StatusCodes.TWO_ZERO_ONE.getValue(), mockResponse.getStatus());
		mockResponse.resetBuffer();
		

	}
	
	@Test
	public void testValidationFailure() {

		
		reset(transactionDataServiceImpl);
		TransactionRequest request = new TransactionRequest();
		request.setAmount(42.00);
		request.setTimestamp(System.currentTimeMillis());
		
		when(transactionDataServiceImpl.saveTransaction(request, transMap, System.currentTimeMillis())).thenReturn(true);
		when((commonUtil.getCurrentTimeInMillis())).thenReturn(System.currentTimeMillis());
		when(validation.validate(request)).thenReturn(false);

		controller.saveTransaction(request, mockRequest, mockResponse);
		
		assertEquals(StatusCodes.FOUR_HUNDRED.getValue(), mockResponse.getStatus());
		mockResponse.resetBuffer();
	}

	
	@Test
	public void testSaveTransOlderThanSixtySeconds() {
		
		reset(transactionDataServiceImpl);		
		TransactionRequest request = new TransactionRequest();
		request.setAmount(42.00);
		request.setTimestamp(1509799806149L);
		
		when(transactionDataServiceImpl.saveTransaction(request, transMap, System.currentTimeMillis())).thenReturn(true);
		when((commonUtil.getCurrentTimeInMillis())).thenReturn(System.currentTimeMillis());
		when(validation.validate(request)).thenReturn(true);

		controller.saveTransaction(request, mockRequest, mockResponse);
		assertEquals(StatusCodes.TWO_ZERO_FOUR.getValue(), mockResponse.getStatus());
		mockResponse.resetBuffer();

	}	

/*	@Test
	public void testGenerateStatistics() throws JsonProcessingException {
		
		reset(transactionDataServiceImpl);
		transMap.clear();
		transMap.put(System.currentTimeMillis(),42.00);
		//when(transactionDataServiceImpl.getTransactionStatistics(transMap, System.currentTimeMillis()));
		when((commonUtil.getCurrentTimeInMillis())).thenReturn(System.currentTimeMillis());

		String statistics = controller.generateStatistics(mockRequest, mockResponse);
		
		TransactionResponse response = new TransactionResponse();
		response.setSum(42.00);
		response.setAvg(42.00);
		response.setMax(42.00);
		response.setMin(42.00);
		response.setCount(1);
		
		ObjectMapper objMapper = new ObjectMapper();
		
		assertEquals(objMapper.writeValueAsString(response), statistics);
		assertEquals(StatusCodes.TWO_HUNDRED.getValue(), mockResponse.getStatus());
		
		mockResponse.resetBuffer();

	}	*/
	
	

}
