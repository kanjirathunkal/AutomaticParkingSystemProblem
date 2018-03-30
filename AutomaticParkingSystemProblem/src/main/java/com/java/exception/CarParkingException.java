package com.java.exception;



public abstract class CarParkingException extends Exception{

	private static final long serialVersionUID = -6292906838198445017L;
	private String message;
	
	
	public CarParkingException(String message) {
		super();
		this.message = message;
	}


	public String getMessage() {
		return message;
	}

}
