package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;

import static edu.nyu.pqs.impl.Operand.operand;

// Package-protected subclass of Operator.  Implements exponentiation.
class Exp extends Operator {

  Exp(OpEnum op, VisitableNode left, VisitableNode right) {
    super(op, left, right);
  }
  
  // Raises the first Operand to the power of the second.
  @Override
  public Operand apply(Operand first, Operand second) {
    return operand(Math.pow(first.getValue(), second.getValue()));
  }

}