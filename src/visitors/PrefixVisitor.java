/**
 * 
 */
package edu.nyu.pqs.visitors;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Visitor for creating prefix formatted outputs from Operators and Operands.
 * Should be called on the output from the Parser class.
 * @author bill
 * @see Parser
 */
public class PrefixVisitor implements Visitor {

  private List<Visitable> prefix;
  
  public PrefixVisitor() {
    prefix = new ArrayList<Visitable>();
  }
  
  @Override
  public void visit(Operand operand) {
    prefix.add(operand);
  }

  @Override
  public void visit(Operator operator) {
    prefix.add(operator);
    operator.getLeft().accept(this);
    operator.getRight().accept(this);
  }

  /**
   * Returns a String representing the expression in prefix notation
   * @return A string representing the expression in prefix notation
   */
  public String result() {
    StringBuildVisitor sbVisitor = new StringBuildVisitor();
    for (Visitable v : prefix) {
      v.accept(sbVisitor);
    }
    return sbVisitor.result();
  }

}