package edu.nyu.pqs.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the high level calculator.  As most normal expressions are tested at
 * lower levels, these tests tend to be based around odd cases.
 * @author bill
 *
 */
public class CalculatorTest {

  Calculator calculator;
  double delta = .000001;
  
  @Before
  public void setUp() throws Exception {
    calculator = new Calculator();
  }

  @Test
  public void testEvaluate() {
    StringBuilder sb = new StringBuilder();
    sb.append("2");
    for (int i = 0; i < 3999; i++) {
      sb.append(" + 2");
    }
    assertEquals(calculator.evaluate(sb.toString()), 8000, delta);
  }

  @Test
  public void testPostfix() {
    assertEquals(calculator.postfix("   -8   +    -2 - (-2   / 5)  "), "-8.0 -2.0 + -2.0 5.0 / -");
  }

  @Test
  public void testPrefix() {
    assertEquals(calculator.prefix("-8 / (1 - 3000) "),"/ -8.0 - 1.0 3000.0");
  }

  @Test
  public void testInfix() {
    assertEquals(calculator.infix("((2 + 2) + -2) ^2  "),
        "(2.0 + 2.0 + -2.0) ^ 2.0");
  }
}
