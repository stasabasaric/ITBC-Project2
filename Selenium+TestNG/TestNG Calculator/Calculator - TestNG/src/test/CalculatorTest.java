package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import calculator.Calculator;

public class CalculatorTest {
	
	@Test
	public void getTest() {
		Calculator calc = new Calculator();
		 Assert.assertEquals(calc.getValue(), 0d);
		
	}
	
	
	@Test
	public void setTest() {
		Calculator calc = new Calculator();
		calc.setValue(100d);
		
		 Assert.assertEquals(calc.getValue(), 100d);
		
	}

	
  @Test 
  public void addTest() {
	  
	  Calculator calc = new Calculator();
	  calc.add(-2.2);
	  
	  Assert.assertEquals(calc.getValue(), -2.2);
	  
	  
  }
  
  @Test
  public void divTest() {
	  
	  Calculator calc = new Calculator();
	  SoftAssert sa = new SoftAssert();
	 
	  calc.div(0);
	  sa.assertEquals(calc.getValue(), 0d);
	  
	  calc.div(-200);
	  sa.assertEquals(calc.getValue(), -0d);
	  
	  calc.setValue(10.0);
	  calc.div(3);
	  
	  sa.assertEquals(calc.getValue(), 10d/3d);
	  
	  sa.assertAll();
	  
  }
  
  
}
