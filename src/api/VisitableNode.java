package edu.nyu.pqs.api;

/**
 * This interface is used to create a Visitable binary tree.  All Object
 * nodes in a Visitable tree should implement this interface.  The leaves
 * should throw UnsupportedOperationException for the getLeft and getRight
 * methods.
 * @author bill
 * @see Visitable
 * @see Visitor
 * @see Operator
 * @see Operand
 */
public interface VisitableNode extends Visitable{

  /**
   * Gets the left child of this VisitableNode
   * @return this VisitableNode's left child
   */
  public VisitableNode getLeft();
  
  /**
   * Gets the right child of this VisitableNode
   * @return this VisitableNode's right child
   */
  public VisitableNode getRight();
  
}
