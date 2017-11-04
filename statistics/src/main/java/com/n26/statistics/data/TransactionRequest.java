package com.n26.statistics.data;

import org.springframework.stereotype.Component;


/**
 * 
 * This class is used to prepare request objects upon receiving request.
 * 
 * @author Ajay
 *
 */

@Component
public class TransactionRequest {
	
	private double amount;
	private long timestamp;
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionRequest [amount=" + amount + ", timestamp=" + timestamp + "]";
	}
	
	

}
