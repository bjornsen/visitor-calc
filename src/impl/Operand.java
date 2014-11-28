/**
 * 
 */
package edu.nyu.pqs.impl;

import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.api.Visitor;

/**
 * A VisitableNode for encapsulating all the information needed in 
 * Operands.  Deals only in doubles.  As Operands are leaves, this
 * class does not have any children and, if accessed, throws 
 * UnsupportedOperationException.
 * @author bill
 * @see VisitableNode
 */
public class Operand implements VisitableNode {
  
  private final double number;
  
  private Operand(double number) {
    this.number = number;
  }
  
  /**
   * Static factory method for creating Operands.
   * @param number The number backing this Operand
   * @return And Operand with value equal to number
   */
  public static Operand operand(double number) {
    return new Operand(number);
  }
  
  @Override
  public void accept(Visitor visitor) {
    visitor.visit(this);
  }
  
  /**
   * Method to get the value of this Operand
   * @return Returns the double value of this Operand
   */
  public double getValue() {
    return number;
  }
  
  @Override
  public String toString() {
    return String.valueOf(number);
  }
  
  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (this == other) {
      return true;
    }
    
    if (!(other instanceof Operand)) {
      return false;
    }
    Operand otherOperand = (Operand) other;
    
    if (this.getValue() == otherOperand.getValue()) {
      return true;
    }
    else {
      return false;
    }
  }
  
  @Override
  public int hashCode() {
    int hash = 1;
    int mult = 17;
    hash = hash * mult + (int) number;
    hash = hash * mult;
    return hash;
    
  }

  /**
   * Not implemented for Operand leaves.
   * @throws UnsupportedOperationException
   */
  @Override
  public VisitableNode getLeft() {
    throw new UnsupportedOperationException("This is a leaf node.");
  }

  /**
   * Not implemented for Operand leaves.
   * @throws UnsupportedOperationException
   */
  @Override
  public VisitableNode getRight() {
    throw new UnsupportedOperationException("This is a leaf node.");
  }
}
