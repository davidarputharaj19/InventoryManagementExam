package com.inventory.product.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BlockedProductException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ErrorInfo handleBlockedProductException(BlockedProductException bpe){
		return new ErrorInfo(bpe.getErrorMessage(), bpe.getErrorDescription(), bpe.getErrorTimeStamp());
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorInfo handleResourceNotFoundException(ResourceNotFoundException bpe){
		return new ErrorInfo(bpe.getErrorMessage(), bpe.getErrorDescription(), bpe.getErrorTimeStamp());
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorInfo handleException(Exception e){
		return new ErrorInfo(e.getMessage(), e.getMessage(), LocalDateTime.now());
	}

	
	
}
