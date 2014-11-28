package edu.nyu.pqs.operators;

/**
 * Enum representing the various types of Operators and their infix precedence
 * @author bill
 *
 */
public enum OpEnum {
  PLUS ("+", 1),
  MINUS ("-", 1),
  MULT ("*", 2),
  DIV ("/", 2),
  EXP ("^", 3),
  LPAREN("(", 0),
  RPAREN(")", 0);
  
  private final String operator;
  private final int precedence;
  
  OpEnum(String operator, int precedence) {
    this.operator = operator;
    this.precedence = precedence;
  }
  
  /**
   * Returns the precedence of this OpEnum.
   * @return The infix calculation precedence of this OpEnum
   */
  public int precedence() {
    return precedence;
  }
  
  /**
   * Returns the Enum associated with the value of a string passed in.
   * For example:  getEnum("+") would return PLUS
   * @param value A String representing an operator
   * @return The enum representation of the value string
   * @throws IllegalArgumentException If the passed value is not an
   * operator.
   */
  public static OpEnum getEnum(String value) {
    if (value == null) {
      throw new IllegalArgumentException("Null string passed in.");
    }
    
    for (OpEnum op : values()) {
      if (value.equals(op.toString())) {
        return op;
      }
    }
    
    throw new IllegalArgumentException("The string " + value + " does not" +
        " match a valid OpEnum operator.");
  }
  
  @Override
  public String toString() {
    return operator;
  }
}
