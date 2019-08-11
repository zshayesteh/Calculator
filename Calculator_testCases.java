/**
 * 
 */
package HW10_calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zahra
 *
 */
public class Calculator_testCases {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	/*
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	 */
	@Test
	public void ExpressionValidation1(){
		Calculator calculator=new Calculator("(1pp)");
		assertFalse("this expression should return false",calculator.validateExpression());
	}
	@Test
	public void ExpressionValidation2(){
		Calculator calculator=new Calculator("(1++++2)");
		assertTrue("this expression should return true",calculator.validateExpression());
	}
	@Test
	public void testPostfixExpression1(){
		Calculator calculator=new Calculator("1+2");
		assertEquals("1 2  + ",calculator.makePostfixExpression("1+2"));
	}
	@Test
	public void testPostfixExpression2(){
		Calculator calculator=new Calculator("1+2");
		assertEquals("12+",calculator.makePostfixExpression("1+2"));
	}
	@Test
	public void testPostfixExpression3(){
		Calculator calculator=new Calculator("(1++++2)");
		assertTrue("this expression should return true",calculator.validateExpression());
	}
	@Test
	public void testPostfixExpression4(){
		Calculator calculator=new Calculator("cos3+2*5");
		String postfixExpression=calculator.makePostfixExpression("cos3+2*5");
		assertEquals("3  cos 2 5  *  + ",postfixExpression);
	}
	@Test
	public void calculateExpressionMultipleSigns(){
		Calculator calculator=new Calculator("(1++++2)");
		assertEquals(3, calculator.calculate());
	}
	@Test
	public void calculateExpressionNull(){
		Calculator calculator=new Calculator(null);
		assertEquals("", calculator.calculate());
	}
	@Test
	public void calculateExpressionSpace(){
		Calculator calculator=new Calculator("");
		assertEquals("input expression is not valid!", calculator.calculate());
	}
	@Test
	public void identifyChar(){
		Calculator calculator=new Calculator("2+4/3");
		assertEquals("should pass",8, calculator.identifyChar("/"));
	}

}
