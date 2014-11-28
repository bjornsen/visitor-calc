package edu.nyu.pqs.api;

import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Interface used to implement the visitor pattern for Operators and
 * Operands.  
 * @author bill
 * @see Operand
 * @see OperandFactory
 * @see Operator
 */
public interface Visitor {
  
  /**
   * Visits an Operand and performs the action given when overriding
   * this method.
   * @param operand An Operand this Visitor has visited
   * @return 
   * @see Operand
   */
  public void visit(Operand operand);
  
  /**
   * Visits an Operator and performs the action given when overriding
   * this method.
   * @param operator An Operator this Visitor has visited.
   * @see Operator
   */
  public void visit(Operator operator);
  
}