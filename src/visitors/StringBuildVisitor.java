package edu.nyu.pqs.visitors;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Simple visitor that builds formatted expression strings out of a list
 * of Operators and Operands.  
 * @author bill
 *
 */
public class StringBuildVisitor implements Visitor {
  
  StringBuilder sb = new StringBuilder();
  
  /* Private helper to make sure no white space is added at the start of
   * an expression.
   */
  
  private static void appendWithFormatting(StringBuilder sb, Visitable v) {
    if (sb.length() == 0) {
      sb.append(v.toString());
    }
    else {
      sb.append(" " + v.toString());
    }
  }

  @Override
  public void visit(Operand operand) {
    appendWithFormatting(sb, operand);

  }

  @Override
  public void visit(Operator operator) {
    appendWithFormatting(sb, operator);
  }
  
  /**
   * Returns a String representing the visited Operators and Operands
   * @return a String representation of the visited Operators and Operands
   */
  public String result() {
    return sb.toString();
  }

}
