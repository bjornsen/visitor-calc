/**
 * 
 */
package edu.nyu.pqs.visitors;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.impl.Operand;
import edu.nyu.pqs.operators.Operator;

/**
 * A visitor that preforms the Shunting-Yard algorithm on operators and
 * Operands.
 * @author bill
 * @see Operand
 * @see Operator
 * @see OperatorFactory
 */
public class ShuntingYardVisitor implements Visitor {

  private Deque<Operator> opStack = new ArrayDeque<Operator>();
  private List<Visitable> rpn = new ArrayList<Visitable>();

  @Override
  public void visit(Operand operand) {
    rpn.add(operand);
  }

  @Override
  public void visit(Operator operator) {
    if (operator.is(")")) {
      Operator out = opStack.pop();
      while (!out.is("(")) {
        rpn.add(out);
        out = opStack.pop();
      }
    }
    else {
      if (opStack.size() == 0 || operator.is("(")) {
        opStack.push(operator);
      }
      else {
        while (opStack.size() > 0) {
          Operator nextOp = opStack.peek();
          /* If nextOp is left associative and the precedence of
           * op is less than or equal to nextOp OR op has less
           * precedence than nextOp. Pop the opStack and add it to
           * the reverse polish notation list.
           */
          if (((nextOp.is("+") || nextOp.is("*")) && operator.compareTo(nextOp) <= 0)
              || operator.compareTo(nextOp) < 0) {
            rpn.add(opStack.pop());
          }
          else {
            break;
          }
        }
        opStack.push(operator);
      }
    }
  }

  /**
   * Returns the postfix result of the Shunting-Yard algorithm after this
   * Visitor has finished visiting.
   * @return A List<Visitable> in postfix order
   */
  public List<Visitable> result() {
    // Add an operators left on the stack to the RPN list
    for (Operator op : opStack) {
      rpn.add(op);
      opStack.remove(op);
    }
    return new ArrayList<Visitable>(rpn);
  }

}