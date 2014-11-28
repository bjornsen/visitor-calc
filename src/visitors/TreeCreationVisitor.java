package edu.nyu.pqs.visitors;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;
import static edu.nyu.pqs.operators.OperatorFactory.operator;

/**
 * Creates a VisitableNode tree out of a set of postfix ordered Operators and 
 * Operands. Should be used on the output of the Shunting-Yard Visitor
 * @author bill
 * @see ShuntingYarddVisitor
 */
public class TreeCreationVisitor implements Visitor {
  private Deque<VisitableNode> stack = new ArrayDeque<VisitableNode>();

  @Override
  public void visit(Operand operand) {
    stack.push(operand);
  }

  @Override
  public void visit(Operator operator) {
    VisitableNode vn2 = stack.pop();
    VisitableNode vn1 = stack.pop();
    stack.push(operator(operator,vn1,vn2));
  }

  /**
   * Returns a VisitableNode binary tree of the visited set of Operators
   * and Operands
   * @return a VisitableNode with a nested binary tree
   */
  public VisitableNode result() {
    try {
      return stack.pop();
    }
    catch (NoSuchElementException e) {
      throw new IllegalStateException("I haven't visited anyone yet.");
    }
  }
}
