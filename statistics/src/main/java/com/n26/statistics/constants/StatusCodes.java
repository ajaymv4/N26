package com.n26.statistics.constants;

/**
 * Enums to set status codes in response.
 * 
 * @author Ajay
 *
 */
public enum StatusCodes {

	TWO_ZERO_ONE(201), TWO_ZERO_FOUR(204), FOUR_HUNDRED(400), TWO_HUNDRED(200);
	
	
	private int value;

	private StatusCodes(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
