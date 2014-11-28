/**
 * 
 */
package edu.nyu.pqs.operators;

import static edu.nyu.pqs.impl.Operand.operand;
import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;

// Package-protected subclass of operator used for subtraction
class Sub extends Operator {
  
  Sub(OpEnum op, VisitableNode left, VisitableNode right) {
    super(op, left, right);
  }
  
  // Subtracts the second Operand from the first.
  @Override
  public Operand apply(Operand first, Operand second) {
    // TODO Auto-generated method stub
    return operand(first.getValue() - second.getValue());
  }

}