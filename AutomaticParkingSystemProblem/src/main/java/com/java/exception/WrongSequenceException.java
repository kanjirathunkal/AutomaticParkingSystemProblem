package com.java.exception;



public class WrongSequenceException extends CarParkingException{

	private static final long serialVersionUID = 1776653458238437390L;
	
	
	public WrongSequenceException(String message) {
		super(message);
	}

}
