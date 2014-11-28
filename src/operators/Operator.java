/**
 * 
 */
package edu.nyu.pqs.operators;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;

/**
 * Abstract superclass for operators.  Implements all operator functionality
 * besides the apply method which is left to the subclasses.  The {@link #apply} method
 * in subclasses should be written such that it applies that operator to the first and
 * second operands.
 * @author bill
 * @see OperatorFactory
 * @see OpEnum
 */
public abstract class Operator implements Visitable, Comparable<Operator>, VisitableNode {

  protected final OpEnum operator;
  private final VisitableNode left;
  private final VisitableNode right;

  protected Operator(OpEnum operator) {
    this.operator = operator;
    this.left = null;
    this.right = null;
  }

  protected Operator(OpEnum operator, VisitableNode left, VisitableNode right) {
    //TODO Change this to accept the OpEnum
    this.operator = operator;
    this.left = left;
    this.right = right;
  }

  /**
   * Returns the left child VisitableNode of this Operator
   * @return this Operator's left child VisitableNode
   */
  public VisitableNode getLeft() {
    return left;
  }

  /**
   * Returns the right child VisitableNode of this Operator
   * @return this Operator's right child VisitableNode
   */
  public VisitableNode getRight() {
    return right;
  }

  /**
   * Override this method when creating a new Operator subclass.  It applies 
   * the representative subclass Operator to the first and second Operands
   * and returns an Operand as the result.
   * @param first The left Operand in an expression
   * @param second The right Operand in an expression
   * @return An Operand representing the expression evaluation of (first 
   * operator second)
   * @see Operand
   * @see Operator
   */
  public abstract Operand apply(Operand first, Operand second);

  /**
   * Takes in an string and returns whether the given Operator matches that
   * string.  If {@code operator} is an addition operator
   * {@code operator.is("+")} returns true. 
   * @param opQuery A string representing an operator to match
   * @return True if the opQuery matched this operator.  False otherwise.
   */
  public boolean is(String opQuery) {
    if (operator.toString().equals(opQuery)) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
   * Used to return the precedence of an operator.  Look at OpEnum for 
   * definitions of precedence for each operator.
   * @return A number representing the relative precedence of this operator
   * @see OpEnum
   */
  public int getPrecedence() {
    return operator.precedence();
  }

  /**
   * Gets a string representation of this operator
   * @return A string representation of this operator
   */
  public String getOperator() {
    return operator.toString();
  }

  @Override
  public void accept(Visitor visitor) {
    // TODO Auto-generated method stub
    visitor.visit(this);
  }

  /**
   * Compares operators based on their infix precedence.
   */
  @Override
  public int compareTo(Operator operator) {
    return this.operator.precedence() - operator.getPrecedence();
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other == this) {
      return true;
    }

    if (!(other instanceof Operator)) {
      return false;
    }
    Operator otherOperator = (Operator) other;

    if (this.getLeft().equals(otherOperator.getLeft()) && this.getRight().equals(otherOperator.getRight())) {
      return true;
    }
    else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    int hash = 1;
    int mult = 13;
    hash = hash * mult + operator.precedence();
    hash = hash * mult + operator.hashCode();
    return hash;
  }

  @Override
  public String toString() {
    return operator.toString();
  }
}
