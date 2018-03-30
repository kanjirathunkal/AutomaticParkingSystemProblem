package com.java.bean;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CarParkingBeanTest {

    @Autowired
    private CarParkingBean ap;	
	
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
	@Test
	public void success1() {
		CarParkingRules ps = ap.calculateNewPosition("5,5:RFLFRFLF");
		
		assertEquals("(5,5)", ps.getInitialPosition());
		assertEquals("RFLFRFLF", ps.getSequence());
		assertEquals("(7,7)", ps.getCurrentPosition());
	}
	
	@Test
	public void success2() {
		CarParkingRules ps = ap.calculateNewPosition("6,6:FFLFFLFFLFF");
		
		assertEquals("(6,6)", ps.getInitialPosition());
		assertEquals("FFLFFLFFLFF", ps.getSequence());
		assertEquals("(6,6)", ps.getCurrentPosition());
	}
	
	@Test
	public void success3() {
		CarParkingRules ps = ap.calculateNewPosition("5,5:FLFLFFRFFF");
		
		assertEquals("(5,5)", ps.getInitialPosition());
		assertEquals("FLFLFFRFFF", ps.getSequence());
		assertEquals("(4,1)", ps.getCurrentPosition());
	}

	@Test 
	public void badCommandTest() {
		
		CarParkingRules ps = ap.calculateNewPosition("KAKAKARFLFRFLF");
		assertEquals(null, ps);
		assertTrue(ap.isError());
		assertEquals("Command format unexpected: KAKAKARFLFRFLF", ap.getErrorMsg());
	}
	
	@Test 
	public void badInitialPositionTest() {
		
		CarParkingRules ps = ap.calculateNewPosition("5:RFLFRFLF");
		assertEquals(null, ps);
		assertTrue(ap.isError());
		assertEquals("Initial position format unexpected: 5", ap.getErrorMsg());
	}
	
	@Test
	public void badSequenceTest() {
		CarParkingRules ps = ap.calculateNewPosition("5,3:RFKLFRFLF");
		assertEquals(null, ps);
		assertTrue(ap.isError());
		assertEquals("The sequence contains illegal characters", ap.getErrorMsg());
	}
	
	@Test
	public void positionOutOfBoundariesTest() {
		CarParkingRules ps = ap.calculateNewPosition("5,5:RRFFFFFF");
		assertEquals(null, ps);
		assertTrue(ap.isError());
		assertEquals("Component Y out of boundaries: -1", ap.getErrorMsg());

	}

}
