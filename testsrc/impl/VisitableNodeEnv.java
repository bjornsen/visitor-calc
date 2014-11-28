package edu.nyu.pqs.impl;

import org.junit.Before;

import edu.nyu.pqs.api.VisitableNode;

/**
 * Sets up a test environment for InfixVisitor, PostfixVisitor,
 * PrefixVisitor, and Calculator tests.  
 * @author bill
 *
 */

public abstract class VisitableNodeEnv {
  
  VisitableNode ret;
  protected VisitableNode easy;
  protected VisitableNode medium;
  protected VisitableNode hard;
  Parser parser;
  protected double delta = .0000001;

  @Before
  public void setUp() throws Exception {
    parser = new Parser();
    easy = parser.parse("2+2");
    medium = parser.parse("(10 ^ (-200 / -100)) / (5 - -5)");
    hard = parser.parse("  (  (  -15 / (7 - (1 + 1) ) ) *  3) -(2+ (-1 +1)   ) ");
  }

}
