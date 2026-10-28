package com.amazon.restapi.exception;

public class OrderNotFoundException extends RuntimeException{

	public OrderNotFoundException(String _message) {
		super(_message);
		
	}

}
