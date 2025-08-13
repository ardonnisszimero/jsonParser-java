package com.parser;

public class JsonToken {
  public static enum TokenType {
    LEFT_BRACKET("LEFT_BRACKET"), 
    RIGHT_BRACKET("RIGHT_BRACKET");

    private String type; 

    private TokenType(String type) {
      this.type = type;
    }
    
    public String getName() {
      return type; 
    }
 }

  private TokenType tokenType; 
  private String value; 

  public JsonToken(TokenType tokenType, String value) {
    this.tokenType = tokenType; 
    this.value = value;
  }

  public String getValue() {
    return value; 
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  @Override
  public String toString() {
      return tokenType.getName() + " : " + value;
  }

  @Override
  public boolean equals(Object obj) {
    JsonToken objTokenType = (JsonToken) obj; 
    return this.tokenType == objTokenType.tokenType && this.getValue().equals(objTokenType.getValue());
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
