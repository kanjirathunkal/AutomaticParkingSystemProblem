package com.java.bean;

import com.java.exception.CarParkingException;
import com.java.exception.PositionBoundariesException;
import com.java.exception.WrongDirectionException;
import com.java.exception.WrongSequenceException;
import com.java.exception.WrongFirstPositionException; 

import com.java.logic.CarParkingLogic; 

public class CarParkingRules {
  
    
	public static final String VALID_INSTRUCTIONS = "FLR";
	
	/* Boundaries */
	private int boundary_x;
	private int boundary_y;
	
	/* Initial coordenates */
	private int initialX;
	private int initialY;
	
	/* Current direction */
	private CarParkingLogic direction;
	
	/* Sequence of commands */
	private String sequence;
	
		
	/* Current coordenates */
	private int currentX;
	private int currentY;
	
	/* Constructor */
	public CarParkingRules(String initStr, String seq, CarParkingLogic direction, int boundX, int boundY) 
			throws WrongSequenceException, WrongFirstPositionException, PositionBoundariesException {
		this.boundary_x = boundX;
		this.boundary_y = boundY;
		this.setInitialPosition(initStr);
		this.setSequence(seq);
		this.currentX = initialX;
		this.currentY = initialY;
		this.direction = direction;
	}
	
	
	/* Method to calculate the final position */
	public void calculateFinalPosition() throws PositionBoundariesException {

		for(int i = 0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			
					switch (c){
				case 'F': moveForward(); break;
				case 'R': setDirection(direction.turnRight()); break;
				case 'L': setDirection(direction.turnLeft()); break;
			}
			
		}
		
	}


	/* Getters & Setters */
	public int getInitialX() {
		return initialX;
	}
	
	public void setInitialX(int initialX) {
		this.initialX = initialX;
	}
	
	public int getInitialY() {
		return initialY;
	}
	
	public void setInitialY(int initialY) {
		this.initialY = initialY;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	/* This method validates the input to ensure the sequence is valid */
	public void setSequence(String sequence) throws WrongSequenceException {
		if (validSequence(sequence))
			this.sequence = sequence;
		else throw new WrongSequenceException("The rules contains illegal characters");
	}

	public String getInitialPosition() {
		return toPosition(initialY, initialX);
	}
	
	public String getCurrentPosition() {
		return toPosition(currentY, currentX);
	}
	
	public CarParkingLogic getDirection() {
		return direction;
	}

	public void setDirection(CarParkingLogic direction) {
		this.direction = direction;
	}


	
	/** 
	 * Method to print the position. 
	 * The position is going to print in (Y,X) format 
	 **/
	private String toPosition(int y, int x) {
		return new StringBuffer()
				.append("(")
				.append(y)
				.append(",")
				.append(x)
				.append(")")
				.toString();			
	}
	
	/**
	 * Method to set the initial position.
	 * This method validates the position as well to be sure is valid.
	 * 
	 * @param initStr
	 * @throws WrongFirstPositionException
	 * @throws PositionBoundariesException
	 */
	private void setInitialPosition(String initStr) throws WrongFirstPositionException, PositionBoundariesException {
		try {
			String coords [] = initStr.split(",");
			this.setInitialX(Integer.valueOf(coords[0]));
			this.setInitialY(Integer.valueOf(coords[1]));
			checkBoundaries(initialX, initialY);
			
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			throw new WrongFirstPositionException("First position format unexpected: " + initStr);
		} 
	}
	
	/**
	 * Method to validate the sequence.
	 * @param seq
	 * @return
	 */
	private boolean validSequence(String seq) {
		return seq.chars()
			.mapToObj(c -> (char) c)
			.allMatch(c -> VALID_INSTRUCTIONS.contains(String.valueOf(c)));
	}
	 
	 
	/**
	 * Method to move forward the position depending on the orientation 
	 * @throws PositionBoundariesException
	 */
	public void moveForward() throws PositionBoundariesException {

		switch(direction) {
		case NORD: currentY++;
				  checkBoundaries(currentX,currentY);
				  break;
				  
		case SOUTH: currentY--;
		  		  checkBoundaries(currentX,currentY);
		  		  break;
			
		case EAST: currentX++;
		  		  checkBoundaries(currentX,currentY);
		  		  break;
			
		case WEST: currentX--;
		  		  checkBoundaries(currentX,currentY);
		  		  break;
		}	
	}
	
	/**
	 * Method to check if the position is a valid one.
	 * @param x
	 * @param y
	 * @throws PositionBoundariesException
	 */
	private void checkBoundaries(int x, int y) throws PositionBoundariesException {
		if (x < 0 || x > boundary_x -1)
			throw new PositionBoundariesException("Component X out of boundaries: " + x);
		
		if (y < 0 || y > boundary_y -1)
			throw new PositionBoundariesException("Component Y out of boundaries: " + y);
	}

}
