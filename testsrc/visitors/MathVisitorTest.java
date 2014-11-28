package edu.nyu.pqs.visitors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.nyu.pqs.impl.VisitableNodeEnv;

public class MathVisitorTest extends VisitableNodeEnv {

  MathVisitor mathVisitor;
  
  @Before
  public void setUp() throws Exception {
    super.setUp();
    mathVisitor = new MathVisitor(); 
  }

  @Test
  public void testEasy() {
    easy.accept(mathVisitor);
    assertEquals(mathVisitor.result(), 4, delta);    
  }

  @Test
  public void testMedium() {
    medium.accept(mathVisitor);
    assertEquals(mathVisitor.result(), 10, delta);    
  }

  @Test
  public void testHard() {
    hard.accept(mathVisitor);
    assertEquals(mathVisitor.result(), -11, delta);    
  }

}
