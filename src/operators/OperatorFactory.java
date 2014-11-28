package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.VisitableNode;

/**
 * Static factory for creating Operators.  The static method
 * {@code operator} takes in a String representing an operator and gives
 * back an Operator of the appropriate type.  For example: {@code 
 * operator("+");}  Will return an Operator whose apply method 
 * performs addition. 
 * @author bill
 * @see Operator
 */
public class OperatorFactory {
  
  /**
   * Constructs a new Operator out of a passed in Operator
   * and two child VisitableNodes.  Mainly used for altering the children
   * of an Operator in the tree.
   * @param operator an Operator that needs new children
   * @param left the new left child VisitableNode
   * @param right the new right child VisitableNode
   * @return the passed in Operator with updated children
   */
  public static Operator operator(Operator operator,
      VisitableNode left, VisitableNode right) {
    return operator(operator.getOperator(), left, right);
  }

  /**
   * For use in contexts where an Operator is needed outside of a tree
   * structure.  When using this version of the static factory, make 
   * sure to refer to the Operator as Visitable.  If it is cast as an
   * Operator or VisitableNode, getLeft() and getRight() will return 
   * null values.
   * @param operator
   * @return
   */
  public static Operator operator(String operator) {
    return operator(operator, null, null);
  }
  
  /**
   * Given an input String representing an operator, this method
   * will return an Operator whose apply method performs the specified
   * functionality.  Valid operator strings are:
   * <ul>
   *   <li> +
   *   <li> -
   *   <li> /
   *   <li> *
   *   <li> ^
   *   <li> (
   *   <li> )
   * </ul>
   * Note that ( and ) do not have an apply method and throw 
   * Unsupported Argument Exception when called
   * @param operator
   * @return
   */
  public static Operator operator(String operator, VisitableNode left, VisitableNode right) {
    OpEnum op = OpEnum.getEnum(operator);
    switch(op) {
    case PLUS:
      return new Add(op, left, right);
    case MINUS:
      return new Sub(op, left, right);
    case DIV:
      return new Div(op, left, right);
    case MULT:
      return new Mult(op, left, right);
    case EXP:
      return new Exp(op, left, right);
    case LPAREN:
      return new LParen();
    case RPAREN:
      return new RParen();
    }
    throw new IllegalArgumentException("Invalid operator input.");
  }

}
