/**
 * 
 */
package edu.nyu.pqs.operator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

import static edu.nyu.pqs.impl.Operand.operand;
import static edu.nyu.pqs.operators.OperatorFactory.operator;

/**
 * Tests the OperatorFactory class and all Operators subclasses
 * including:
 * <ul>
 *   <li>Add</li>
 *   <li>Sub</li>
 *   <li>Div</li>
 *   <li>Mult</li>
 *   <li>Exp</li>
 *   <li>LParen</li>
 *   <li>RParen</li>
 * </ul>
 * @author bill
 *
 */
public class OperatorFactoryTest {

  Operand op1;
  Operand op2;
  double num1 = 5;
  double num2 = 10;
  Operator operator;
  double delta = .00001;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    op1 = operand(num1);
    op2 = operand(num2);
  }

  @Test
  public void addTest() {
    operator = operator("+");
    assertEquals(operator.apply(op1, op2).getValue(), num1 + num2, delta);
  }
  
  @Test
  public void subTest() {
    operator = operator("-");
    assertEquals(operator.apply(op1, op2).getValue(), num1 - num2, delta);  
  }
  
  @Test
  public void divTest() {
    operator = operator("/");
    assertEquals(operator.apply(op1, op2).getValue(), num1 / num2, delta);
  }
  
  @Test
  public void multTest() {
    operator = operator("*");
    assertEquals(operator.apply(op1, op2).getValue(), num1 * num2, delta);
  }
  
  @Test
  public void expTest() {
    operator = operator("^");
    assertEquals(operator.apply(op1, op2).getValue(), Math.pow(num1, num2), delta);
  }
  
  @Test (expected = UnsupportedOperationException.class)
  public void lParenTest() {
    operator = operator("(");
    assertEquals(operator.apply(op1, op2).getValue(), num1 * num2, delta);
  }
  
  @Test (expected = UnsupportedOperationException.class)
  public void rParenTest() {
    operator = operator(")");
    assertEquals(operator.apply(op1, op2).getValue(), num1 * num2, delta);
  }

}
