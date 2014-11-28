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
 * Performs mathematical on a set of Operators and Operands.
 * Should be called on the output of Parser.
 * @author bill
 * @see Parser
 */
public class MathVisitor implements Visitor {
  
  private Deque<Operand> numStack = new ArrayDeque<Operand>();

  @Override
  public void visit(Operand operand) {
    numStack.push(operand);
  }

  @Override
  public void visit(Operator operator) {
    operator.getLeft().accept(this);
    operator.getRight().accept(this);
    Operand op2 = numStack.pop();
    Operand op1 = numStack.pop();
    numStack.push(operator.apply(op1, op2));
  }
  
  /**
   * Returns the evaluated result of the passed in expression after visiting.
   * @return A double representing the evaluation of the expression
   * @throws IllegalStateException When it hasn't visited.
   */
  public double result() {
    try {
      return numStack.pop().getValue();
    }
    catch (NoSuchElementException e) {
      throw new IllegalStateException("I haven't visited anyone yet.");
    }
  }

}
