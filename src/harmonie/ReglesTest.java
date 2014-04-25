package harmonie;

import static org.junit.Assert.*;

import org.junit.Test;

public class ReglesTest extends Regles {

	@Test
	public void testregleInterval() {
		assertEquals(true,regleInterval(11,2));
		assertEquals(true,regleInterval(15,2));
		assertEquals(true,regleInterval(22,2));
		assertEquals(false,regleInterval(10,2));
		assertEquals(false,regleInterval(23,2));
		
		assertEquals(true,regleInterval(7,3));
		assertEquals(true,regleInterval(19,3));
		assertEquals(true,regleInterval(10,3));
		assertEquals(false,regleInterval(6,3));
		assertEquals(false,regleInterval(20,3));
		
		assertEquals(true,regleInterval(3,4));
		assertEquals(true,regleInterval(15,4));
		assertEquals(true,regleInterval(10,4));
		assertEquals(false,regleInterval(2,4));
		assertEquals(false,regleInterval(16,4));
	}
	
	@Test 
	public void testinitialisationListesJeuxRegleUne (){
		
	}
	
	@Test 
	public void testinitialisationListesJeuxRegleeux (){
		
	}
	
	@Test 
	public void testinitialisationListesJeuxRegleQuatre (){
		
	}
	
	@Test
	public void testnombreHarmonisation (){
		
	}

	@Test
	public void testdifference() {
		assertEquals(true, difference(10, 16));
		assertEquals(true, difference(16, 10));
		assertEquals(false, difference(20, 13));
		assertEquals(false, difference(13, 20));
	}

	@Test
	public void testdifferenceRegle() {
		assertEquals(10, differenceRegle(20, 10));
		assertEquals(10, differenceRegle(10, 20));
		assertEquals(0, differenceRegle(10, 10));
	}
}
