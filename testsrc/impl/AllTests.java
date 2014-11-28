package edu.nyu.pqs.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.nyu.pqs.operator.OperatorFactoryTest;
import edu.nyu.pqs.visitors.InfixVisitorTest;
import edu.nyu.pqs.visitors.MathVisitorTest;
import edu.nyu.pqs.visitors.PostfixVisitorTest;
import edu.nyu.pqs.visitors.PrefixVisitorTest;

/**
 * Runs all tests in the test suite.
 * @author bill
 *
 */
@RunWith(Suite.class)
@SuiteClasses({InfixVisitorTest.class,
  MathVisitorTest.class,
  PrefixVisitorTest.class,
  PostfixVisitorTest.class,
  ParserTest.class,
  OperatorFactoryTest.class,
  CalculatorTest.class})
public class AllTests {

}
