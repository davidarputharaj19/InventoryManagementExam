package com.inventory.product.exception;

import java.time.LocalDateTime;

public class ResourceNotFoundException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -166699290228337397L;

	private String errorMessage;
	
	private String errorDescription;
	
	private LocalDateTime errorTimeStamp;

	public ResourceNotFoundException(String errorMessage, String errorDescription, LocalDateTime errorTimeStamp) {
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

	public LocalDateTime getErrorTimeStamp() {
		return errorTimeStamp;
	}

	public void setErrorTimeStamp(LocalDateTime errorTimeStamp) {
		this.errorTimeStamp = errorTimeStamp;
	}
	

}
