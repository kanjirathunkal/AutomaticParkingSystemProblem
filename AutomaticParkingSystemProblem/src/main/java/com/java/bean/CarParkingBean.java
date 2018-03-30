package com.java.bean;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.java.exception.CarParkingException;
import com.java.exception.PositionBoundariesException;
import com.java.exception.WrongDirectionException;
import com.java.exception.WrongSequenceException;
import com.java.exception.WrongFirstPositionException;

import com.java.logic.CarParkingLogic; 

@Component
public class CarParkingBean {

	
	/* Parking boundaries */
	public static final int BOUNDARY_X = 14;
	public static final int BOUNDARY_Y = 14;
	
	/* Error structure */
	private boolean error = false;
	private String errorMsg;
	
	/* Logger */
    private static final Logger logger = LoggerFactory.getLogger(CarParkingBean.class);

    /**
     * Method to prepare the CarParkingRules and calls to calculate the final position
     * @param args
     * @return CarParkingRules
     */
	public CarParkingRules calculateNewPosition(String args) {
		
		CarParkingRules ps;
		
		try {
			ps = createParkingSequenceFromInput(args);	
			ps.calculateFinalPosition();
			
			logger.info("*************");
	        logger.info("First position {} and sequence {}", ps.getInitialPosition(), ps.getSequence());	        
	        logger.info("last Position {}", ps.getCurrentPosition());
	        
	        return ps;
		} catch (WrongFirstPositionException | WrongSequenceException | PositionBoundariesException
				| WrongDirectionException e) {
			this.setError(true);
			this.setErrorMsg(e.getMessage());
			
			logger.info("*************");	        
	        logger.info("Error {}", e.getMessage());
		}
		 
        
		return null;
	}


	/**
	 * Method to validate the input
	 * @param args
	 * @return
	 * @throws WrongFirstPositionException
	 * @throws WrongSequenceException
	 * @throws PositionBoundariesException
	 * @throws WrongDirectionException
	 */
	private CarParkingRules createParkingSequenceFromInput(String args) 
			throws WrongFirstPositionException, WrongSequenceException, PositionBoundariesException, WrongDirectionException {	
		CarParkingRules ps = null;
		try {
			String parts[] = args.split(":");
			String initStr = parts[0];
			String sequence = parts[1];
					
			ps = new CarParkingRules(initStr, sequence, CarParkingLogic.NORD, BOUNDARY_X, BOUNDARY_Y);
		} catch (ArrayIndexOutOfBoundsException e){
			throw new WrongDirectionException("Direction format unexpected: " + args);
		}
		return ps;
	}


	/* Getters & Setters */
	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
