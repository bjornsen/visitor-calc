package edu.nyu.pqs.visitors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.impl.VisitableNodeEnv;

public class InfixVisitorTest extends VisitableNodeEnv {

  InfixVisitor infixVisitor;
  
  @Before
  public void setUp() throws Exception {
    super.setUp();
    infixVisitor = new InfixVisitor();
  }

  @Test
  public void testEasy() {
    easy.accept(infixVisitor);
    assertEquals(infixVisitor.result(), "2.0 + 2.0");
  }
  
  @Test
  public void testMedium() {
    medium.accept(infixVisitor);
    assertEquals(infixVisitor.result(), "10.0 ^ (-200.0 / -100.0) / (5.0 - -5.0)");
  }
  
  @Test
  public void testHard() {
    hard.accept(infixVisitor);
    assertEquals(infixVisitor.result(),"-15.0 / (7.0 - 1.0 + 1.0) * 3.0 - 2.0 + -1.0 + 1.0");
  }

}
