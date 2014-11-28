/**
 * 
 */
package edu.nyu.pqs.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nyu.pqs.api.Visitable;
import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.operators.Operator;
import edu.nyu.pqs.visitors.ShuntingYardVisitor;
import edu.nyu.pqs.visitors.TreeCreationVisitor;
import edu.nyu.pqs.visitors.ValidationVisitor;

import static edu.nyu.pqs.operators.OperatorFactory.operator;
import static edu.nyu.pqs.impl.Operand.operand;

/**
 * A class for parsing mathematical strings into Reverse Polish Notation 
 * (postfix) tokens.  Each token is either an Operator or an Operand. 
 * @author bill
 * @see Operator
 * @see Operand
 * @see OperatorFactory
 */
public class Parser {

  /* Private helper that takes in an expression string and creates tokens
   * out of the numbers and operators.  Checks to make sure there are no
   * invalid characters in the input expression.
   */
  private List<Visitable> tokenize(String expression) {
    List<Visitable> tokens = new ArrayList<Visitable>();
    
    // Strings used for regular expressions
    String reOperators = "\\^\\+\\-\\*/";
    String reOperatorsParens = reOperators + "\\(\\)";

    // Input checking to make sure no invalid characters are passed
    String invalidChars = String.format("[^ \\d\\.%s]", reOperatorsParens);
    Pattern pattern = Pattern.compile(invalidChars);
    Matcher m = pattern.matcher(expression);
    if (m.find()) {
      throw new IllegalArgumentException("Input expression contains " + 
          m.group());
    }

    // Set up regular expressions
    String reDouble = "\\d+\\.\\d+";
    String reInt = "\\d+";
    String regexTokens = String.format(
        "^[ ]*-%1$s|" + // Negative double at the beginning of an expression
        "^[ ]*-%2$s|" + // Negative integer at the beginning of an expression
        "(([%4$s])[ ]*(-%1$s))|" + // Operator followed by negative double
        "(([%4$s])[ ]*((-%2$s)))|" + // Operator followed by negative integer
        "%1$s|" + // Double
        "%2$s|" + // Integer
        "[%4$s]", // Operator or parenthesis
        reDouble, reInt, reOperators, reOperatorsParens);
    
    // Parse Operator and Operand tokens
    pattern = Pattern.compile(regexTokens);
    m = pattern.matcher(expression);
    while(m.find()) {
      /* Group 1 flags an operator followed by a negative integer
       * The operator is stored in group 2 and the negative double
       * is stored in group 3.
       */
      if (m.group(1) != null) {
        tokens.add(operator(m.group(2)));
        tokens.add(operand(Double.parseDouble(m.group(3))));
      }
      /* Group 4 flags an operator followed by a negative integer
       * The operator is stored in group 5 and the negative integer
       * is stored in group 6.
       */
      else if (m.group(4) != null) {
        tokens.add(operator(m.group(5)));
        tokens.add(operand(Double.parseDouble(m.group(6))));
      }
      else {
        String token = m.group(0);
        if (isDouble(token)) {
          tokens.add(operand(Double.parseDouble(token)));
        }
        else {
          tokens.add(operator(token));
        }
      }
    }
    return tokens;
  }

  /* Private helper method that returns true if a String can be parsed to
   * a double and false otherwise.
   */
  private boolean isDouble(String input) {
    try {
      Double.parseDouble(input);
      return true;
    }
    catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Parses a mathematical expression into a VisitableNode tree.  The 
   * VisitablesNodes are either Operands or Operators.
   * @param expression A string representing a mathematical expression in 
   * infix notation
   * @return A VisitableNode tree of Operators and Operand leafs
   * @see Operand
   * @see Operator
   * @see VisitableNode
   */
  public VisitableNode parse(String expression) {
    List<Visitable> tokens = new ArrayList<Visitable>();
    
    // List of tokenized operators and operands in infix notation
    tokens = tokenize(expression);
    
    // Validate the expressions in the tokenized input
    ValidationVisitor vVisitor = new ValidationVisitor(tokens.size());
    for (Visitable token : tokens) {
      token.accept(vVisitor);
    }
    // Validate parentheses
    vVisitor.checkParens();

    // Perform Shunting-Yard algorithm to convert infix token ordering to postfix
    ShuntingYardVisitor shuntVisitor = new ShuntingYardVisitor();
    for (Visitable token : tokens) {
      token.accept(shuntVisitor);
    }
    tokens = shuntVisitor.result();
    
    // Create a tree out of the postfix tokens
    TreeCreationVisitor treeCreationVisitor = new TreeCreationVisitor();
    for (Visitable token : tokens) {
      token.accept(treeCreationVisitor);
    }
    return treeCreationVisitor.result();
  }
}
