/**
 * 
 */
package edu.nyu.pqs.api;

/**
 * This interface must be implemented by all Objects that are Visitable 
 * by Visitors.  It has only one required method - accept.  Which allows
 * the Object to accept a Visitor.  The implemented accept method should
 * include the line: <br>
 *  {@code visitor.visit(this)}
 * @author bill
 * @see Visitor
 */
public interface Visitable {
  
  /**
   * Accepts a Visitor
   * @param visitor A visitor
   * @see Visitor 
   */
  public void accept(Visitor visitor);

}