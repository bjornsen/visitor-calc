/**
 * 
 */
package edu.nyu.pqs.impl;

import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.visitors.TreeCreationVisitor;

import static edu.nyu.pqs.operators.OperatorFactory.operator;
import static edu.nyu.pqs.impl.Operand.operand;
/**
 * Tests the Parser class along with the ShuntingYardVisitor,
 * TreeCreationVisitor and ValidationVisitor.
 * @author bill
 * @see ShuntingYardVisitor
 * @see ValidationVisitor
 * @see TreeCreationvisitor
 * @see Parser
 */
public class ParserTest {

  private Parser parser;
  private VisitableNode ret;
  private VisitableNode expected;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    parser = new Parser();
  }

  /* Private helper method for creating trees out of lists of numbers and
   * Strings.
   */

  private VisitableNode tree(Object... numbersAndOperators) {
    TreeCreationVisitor treeCreationVisitor = new TreeCreationVisitor();
    List<VisitableNode> nodeList = new ArrayList<VisitableNode>();
    for (Object node : numbersAndOperators) {
      try {
        double number = Double.parseDouble(node.toString());
        nodeList.add(operand(number));
      }
      catch (NumberFormatException e) {
        nodeList.add(operator(node.toString()));
      }
    }

    for (VisitableNode node : nodeList) {
      node.accept(treeCreationVisitor);
    }
    return treeCreationVisitor.result();
  }

  /**
   * Test method for {@link edu.nyu.pqs.impl.Parser#parse(java.lang.String)}.
   */
  @Test (expected=IllegalArgumentException.class)
  public void testParse() {
    ret = parser.parse("(2+2)");
    expected = tree(2,2,"+");
    assertEquals(ret,expected);

    ret = parser.parse("-3 + 4 * 2 / (-1 - 5) ^ 2 ^ 3");
    expected = tree(-3,4,2,"*",-1,5,"-",2,3,"^","^","/","+");
    assertEquals(ret,expected);

    ret = parser.parse("((-15 / (7 - (1 + 1))) * 3) - (2 + (1 + 1))");
    expected = tree(-15.0, 7.0, 1.0, 1.0, "+", "-", "/", 3.0, "*", 2.0, 1.0, 1.0, "+", "+", "-");
    assertEquals(ret, expected);
    
    // Check IllegalArgument Exception for an Operator coming first
    ret = parser.parse("+2+2");
  }

  // Check IAE for too many right parentheses
  @Test (expected=IllegalArgumentException.class) 
  public void testExcessiveRParens() {
    ret = parser.parse("(3+4))");
  }

  // Check IAE for right parentheses coming before left
  @Test (expected=IllegalArgumentException.class) 
  public void testEarlyRParens() {
    ret = parser.parse(")3+4()");
  }

  // Check IAE for too many operators in a row
  @Test (expected=IllegalArgumentException.class)
  public void testExcessiveOperators() {
    ret = parser.parse("2++2");
  }

  // Check IAE for an operator coming last
  @Test (expected=IllegalArgumentException.class)
  public void testLastOperator() {
    ret = parser.parse("2+2+");
  }
  
  // Check IAE for too many negatives
  @Test (expected=IllegalArgumentException.class)
  public void testNegatives() {
    ret = parser.parse("2---2");
  }
  
  
}
