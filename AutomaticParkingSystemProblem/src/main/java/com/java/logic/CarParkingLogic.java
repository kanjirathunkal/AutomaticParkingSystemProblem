package com.java.logic;


public enum CarParkingLogic {

	NORD("N"),
	SOUTH("S"),
	EAST("E"),
	WEST("W");
	
	private String point;

	private CarParkingLogic(String point) {
		this.point = point;
	}
	
	public String toString() {
		return point;
	}
	
	/**
	 * This method will return the next CarParkingLogic 
	 * @return CarParkingLogic
	 */
	public CarParkingLogic turnLeft() {
		
		switch (this) {
		case NORD: return CarParkingLogic.WEST;
		case WEST: return CarParkingLogic.SOUTH;
		case SOUTH: return CarParkingLogic.EAST;
		default: return CarParkingLogic.NORD;
		}
	}
	
	/**
	 * This method will return the next CarParkingLogic clockwise
	 * @return CarParkingLogic
	 */
	public CarParkingLogic turnRight() {
		
		switch (this){
		case NORD: return CarParkingLogic.EAST; 
		case EAST: return CarParkingLogic.SOUTH;
		case SOUTH: return CarParkingLogic.WEST;
		default: return CarParkingLogic.NORD; 
		}				
	}
}
