package edu.nyu.pqs.impl;


import edu.nyu.pqs.api.VisitableNode;
import edu.nyu.pqs.api.Visitor;
import edu.nyu.pqs.visitors.InfixVisitor;
import edu.nyu.pqs.visitors.MathVisitor;
import edu.nyu.pqs.visitors.PostfixVisitor;
import edu.nyu.pqs.visitors.PrefixVisitor;

/**
 * A class that evaluates mathematical expressions.  Further, it can
 * print the prefix, postfix, and infix orderings of a given expression.
 * @author bill
 *
 */
public class Calculator {
  
  private final Parser parser;
  
  public Calculator() {
    parser = new Parser();
  }
  
  // Private helper method to parse an expression and dispatch a visitor to it
  private void visitExpression(Visitor visitor, String expression) {
    VisitableNode parsedExpression = parser.parse(expression);
    parsedExpression.accept(visitor);
  }
  
  /**
   * Evaluates the passed in mathematical expression and returns the result
   * as a double.  Supports negative numbers, parentheses, and exponentiation
   * @param expression A string representing a mathematical expression in infix
   * notation.
   * @return A double that results from evaluating the expression.
   */
  public double evaluate(String expression) {
    MathVisitor mathVisitor = new MathVisitor();
    visitExpression(mathVisitor, expression);
    return mathVisitor.result();
  }
  
  /**
   * Converts the expression to postfix aka reverse polish notation.
   * @param expression A string representing a mathematical expression
   * in infix notation.
   * @return A string representing the passed in expression in posfix notation.
   */
  public String postfix(String expression) {
    PostfixVisitor postfixVisitor = new PostfixVisitor();
    visitExpression(postfixVisitor, expression);
    return postfixVisitor.result();
  }
  
  /**
   * Converts the expression to prefix aka polish notation.
   * @param expression A string representing a mathematical expression in infix
   * notation.
   * @return A string representing the passed in expression in prefix notation.
   */
  public String prefix(String expression) {
    PrefixVisitor prefixVisitor = new PrefixVisitor();
    visitExpression(prefixVisitor, expression);
    return prefixVisitor.result();
  }
  
  /**
   * Prints the expression in infix notation.
   * @param expression A string representing a mathematical expression in infix
   * notation.
   * @return A string representing the passed in expression in infix notation.
   */
  public String infix(String expression) {
    // Error check expression then print it back.  Nice.
    InfixVisitor infixVisitor = new InfixVisitor();
    visitExpression(infixVisitor, expression);
    return infixVisitor.result();
  }

}
