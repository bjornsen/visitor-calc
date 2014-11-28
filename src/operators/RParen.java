package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;

// Protected subclass of Operator.  Represents right parentheses.
class RParen extends Operator {

  static OpEnum op = OpEnum.RPAREN;
  /**
   * @param operator
   */
  RParen() {
    super(op);
  }
  
  /**
   * @throws UnsupportedOperationException
   */
  @Override
  public Operand apply(Operand first, Operand second) {
    throw new UnsupportedOperationException("You can't apply a parenthesis" +
    " to an operand!?");
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