package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;

// Package-protected subclass of Operator.  Represents Left Parentheses.
class LParen extends Operator {

  private static OpEnum op = OpEnum.LPAREN;
  
  LParen() {
    super(op);
  }
  
  /**
   * @throws UnsupportedOperationException
   */
  @Override
  public Operand apply(Operand first, Operand second) {
    throw new UnsupportedOperationException("You can't apply a parenthesis to" +
        "operands.");
  }
  
  /**
   * @throws UnsupportedOperationException
   */
  @Override
  public VisitableNode getLeft() {
    throw new UnsupportedOperationException(op.toString() + " has no nodes.");
  }
  
  /**
   * @throws UnsupportedOperationException
   */
  @Override
  public VisitableNode getRight() {
    throw new UnsupportedOperationException(op.toString() + " has no nodes.");
  }
}