package com.java.exception;



public class WrongDirectionException extends CarParkingException{

	private static final long serialVersionUID = -6817322630855570L;
	
	
	public WrongDirectionException(String message) {
		super(message);
	}


}