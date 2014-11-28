/**
 * 
 */
package edu.nyu.pqs.visitors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Visitor for creating infix formatted outputs from Operators and Operands.
 * Used with the VisitableNode tree output from the Parser class.
 * @author bill
 * @see Parser
 */
public class InfixVisitor implements Visitor {

  private Deque<Expression> numStack = new ArrayDeque<Expression>();

  /*
   * Private class for encapsulating a String representing an infix Expression
   * and the precedence value of that Expression.  Allows for parent operators
   * to add parentheses if they have a higher precedence.
   */
  private class Expression {
    private int precedence;
    private String expression;

    Expression(Operand operand) {
      precedence = 10;
      expression = operand.toString();
    }

    Expression(Operator operator, Object left, Object right) {
      precedence = operator.getPrecedence();
      expression = left + " " + operator + " " + right;
    }

    public String toString() {
      return expression;
    }

    /* Adds parentheses to the expression if the operator passed has greater
     * precedence than this expression.
     */
    public void addNecessaryParens(Operator operator) {
      if (operator.getPrecedence() > precedence) {
        expression = "(" + expression + ")";  
      }
    }
  }


  @Override
  public void visit(Operand operand) {
    numStack.push(new Expression(operand));
  }

  @Override
  public void visit(Operator operator) {
    operator.getLeft().accept(this);
    operator.getRight().accept(this);
    Expression exp2 = numStack.pop();
    Expression exp1 = numStack.pop();
    exp1.addNecessaryParens(operator);
    exp2.addNecessaryParens(operator);
    numStack.push(new Expression(operator, exp1, exp2));
  }

  /**
   * Returns a String of the expression in infix notation.  
   * Must be called after the visitor has finished visiting.
   * @return A string in infix notation.
   */
  public String result() {
    /* Since toString on Deque objects insist on surrounding things with brackets
     * the replace is necessary to strip those
     */
    try {
      return numStack.peek().toString().replace("[]", "");
    }
    catch (NoSuchElementException e) {
      throw new IllegalStateException("I haven't visited anyone.");
    }
  }

}