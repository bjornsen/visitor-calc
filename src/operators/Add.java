package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;

import static edu.nyu.pqs.impl.Operand.operand;

// Package-protected subclass of Operator.  Implements addition.
class Add extends Operator {
  
  Add(OpEnum op, VisitableNode left, VisitableNode right) {
    super(op, left, right);
  }
  
  // Adds the first and second operator
  @Override
  public Operand apply(Operand first, Operand second) {
    return operand(first.getValue() + second.getValue());
  }
}