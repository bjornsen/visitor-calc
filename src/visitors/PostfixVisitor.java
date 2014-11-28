package edu.nyu.pqs.visitors;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * Returns a postfix formatted String after visiting Operators and
 * Operands.  Should be called on the output of the Parser class.
 * @author bill
 * @see Parser
 */
public class PostfixVisitor implements Visitor {
  
  private List<Visitable> postfix = new ArrayList<Visitable>();

  @Override
  public void visit(Operand operand) {
    postfix.add(operand);
  }

  @Override
  public void visit(Operator operator) {
    operator.getLeft().accept(this);
    operator.getRight().accept(this);
    postfix.add(operator);
  }
  
  /**
   * Returns a postfix formatted String built from the visited nodes
   * @return a postfix formatted String
   */
  public String result() {
    StringBuildVisitor sbVisitor = new StringBuildVisitor();
    for (Visitable v : postfix) {
      v.accept(sbVisitor);
    }
    return sbVisitor.result();
  }

}
