package com.inventory.product.exception;

public class ErrorInfo extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4068098728747703655L;
	

	private String errorMessage;
	
	private String errorDescription;
	
	private String errorTimeStamp;

	public ErrorInfo(String errorMessage, String errorDescription, String errorTimeStamp) {
		super();
		this.errorMessage = errorMessage;
		this.errorDescription = errorDescription;
		this.errorTimeStamp = errorTimeStamp;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorTimeStamp() {
		return errorTimeStamp;
	}

	public void setErrorTimeStamp(String errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
	}
	
}
