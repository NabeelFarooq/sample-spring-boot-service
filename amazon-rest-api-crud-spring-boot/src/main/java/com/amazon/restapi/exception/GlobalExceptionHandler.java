package com.amazon.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazon.restapi.response.ErrorResponse;
import com.amazon.restapi.response.OrderResponse;
// this class will handle all exceptions
// this will act as controller only and send response back
// advisor to rest controller
// this is applicable to all the controllers
@RestControllerAdvice
public class GlobalExceptionHandler {
	// handler methods
	// if written inside a controller then applicable only for that controller
	// whatever exception comes keep on writing here
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(OrderNotFoundException e){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("ERR-01");
		errorResponse.setMessage(e.getMessage());
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e){
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setErrorCode("ERR-01");
		errorResponse.setMessage(e.getMessage());
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
