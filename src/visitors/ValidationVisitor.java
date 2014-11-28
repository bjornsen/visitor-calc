package edu.nyu.pqs.visitors;

import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Visitor that validates mathematical expressions consisting of Operators
 * and Operands.  Must know the number of visitables in the constructor
 * so that it can validate that the expression does not begin or end with
 * a binary operator.
 * @throws IllegalArgumentException For malformed expressions.
 * @author bill
 * @see Operand
 * @see Operator
 * @see OperatorFactory
 */
public class ValidationVisitor implements Visitor {
  
  private Object prevObject = null;
  private int parenCount = 0;
  private int count = 0;
  private int numVisitables;
  
  /**
   * Constructs this validation visitor with the number of nodes it will
   * visit.
   * @param numVisitables Number of Objects that will be visited
   */
  public ValidationVisitor(int numVisitables) {
    this.numVisitables = numVisitables;
  }
  
  @Override
  public void visit(Operand operand) {
    count += 1;

    // Expression is invalid if there are two Operands in a row
    if (prevObject instanceof Operand) {
      throw new IllegalArgumentException("Invalid input expression.");
    }
    prevObject = operand;
  }

  @Override
  public void visit(Operator operator) {
    count += 1;
    
    // parenCount is used to validate correctly ordered and matched parentheses
    if (operator.is("(")) {
      parenCount += 1;
    }
    else if (operator.is(")")) {
      parenCount -= 1;
    }
    else {
      // Expression is invalid if there are two Operators in a row
      if (prevObject instanceof Operator && !(prevObject.equals("(") || prevObject.equals(")"))||
          count == 1 ||
          count == numVisitables) {
        throw new IllegalArgumentException("Invalid input expression.");
      }
      prevObject = operator;
    }
    

    if (parenCount < 0) {
      throw new IllegalArgumentException("Right parentheses come before" +
          " left parentheses in input expression.");
    }
  }

  /**
   * Called after ValidationVisitor has visited all nodes.  Will check whether
   * the expression has a balanced number of parentheses.
   * @throws IllegalArgumentException If there were not a balanced number of
   * parentheses
   */
  public void checkParens() {
    if (parenCount != 0) {
      throw new IllegalArgumentException("Unbalanced parentheses in input" +
          "experssion.");
    }
  }
}