package com.java.bean;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.exception.CarParkingException;
import com.java.exception.PositionBoundariesException;
import com.java.exception.WrongDirectionException;
import com.java.exception.WrongSequenceException;
import com.java.exception.WrongFirstPositionException;

import com.java.logic.CarParkingLogic; 

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarParkingRulesTest {

	private CarParkingRules ps;
	
	@Before
	public void setup() {
		try {
			ps = new CarParkingRules("5,5","RFLFRFLF", CarParkingLogic.NORD, 15, 15);
		} catch (WrongSequenceException | WrongFirstPositionException | PositionBoundariesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void constructorTest() {
		assertEquals("(5,5)", ps.getInitialPosition());
		assertEquals("(5,5)", ps.getCurrentPosition());
		assertEquals("RFLFRFLF", ps.getSequence());
	}
	
	@Test
	public void moveForwardTest() {
		try {
			ps.setDirection(CarParkingLogic.NORD);
			ps.moveForward();
			assertEquals("(6,5)", ps.getCurrentPosition());
			
			ps.setDirection(CarParkingLogic.EAST);
			ps.moveForward();
			assertEquals("(6,6)", ps.getCurrentPosition());
			
			ps.setDirection(CarParkingLogic.SOUTH);
			ps.moveForward();
			assertEquals("(5,6)", ps.getCurrentPosition());
			
			ps.setDirection(CarParkingLogic.WEST);
			ps.moveForward();
			assertEquals("(5,5)", ps.getCurrentPosition());
			
		} catch (PositionBoundariesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void calculateFinalPositionTest() {
		try {
			ps.calculateFinalPosition();
			
			assertEquals("(5,5)", ps.getInitialPosition());
			assertEquals("(7,7)", ps.getCurrentPosition());
			assertEquals("RFLFRFLF", ps.getSequence());
			
		} catch (PositionBoundariesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
