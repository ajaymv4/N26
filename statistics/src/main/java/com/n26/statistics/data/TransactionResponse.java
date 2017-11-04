package com.n26.statistics.data;


/**
 * 
 * This class is used to while preparing response to be sent.
 * 
 * @author Ajay
 *
 */
public class TransactionResponse {

	private double sum;
	private double avg;
	private double max;
	private double min;
	private long count;
	
	
	
	public TransactionResponse() {
		super();
		this.sum = 0.00;
		this.avg = 0.00;
		this.max = 0.00;
		this.min = 0.00;
		this.count = 0;
	}
	/**
	 * @return the sum
	 */
	public double getSum() {
		return sum;
	}
	/**
	 * @param sum the sum to set
	 */
	public void setSum(double sum) {
		this.sum = sum;
	}
	/**
	 * @return the avg
	 */
	public double getAvg() {
		return avg;
	}
	/**
	 * @param avg the avg to set
	 */
	public void setAvg(double avg) {
		this.avg = avg;
	}
	/**
	 * @return the max
	 */
	public double getMax() {
		return max;
	}
	/**
	 * @param max the max to set
	 */
	public void setMax(double max) {
		this.max = max;
	}
	/**
	 * @return the min
	 */
	public double getMin() {
		return min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(double min) {
		this.min = min;
	}
	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}
	
	

}
