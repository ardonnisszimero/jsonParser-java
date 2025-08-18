package com.parser;

import static com.parser.JsonToken.TokenType.COLON;
import static com.parser.JsonToken.TokenType.COMMA;
import static com.parser.JsonToken.TokenType.DOUBLE_QUOTES;
import static com.parser.JsonToken.TokenType.LEFT_BRACKET;
import static com.parser.JsonToken.TokenType.LEFT_CURLY_BRACKET;
import static com.parser.JsonToken.TokenType.RIGHT_BRACKET;
import static com.parser.JsonToken.TokenType.RIGHT_CURLY_BRACKET;
import static com.parser.JsonToken.TokenType.STRING;

public class JsonToken {
  public static enum TokenType {
    LEFT_CURLY_BRACKET("LEFT_CURLY_BRACKET"), 
    RIGHT_CURLY_BRACKET("RIGHT_CURLY_BRACKET"), 
    LEFT_BRACKET("LEFT_BRACKET"), 
    RIGHT_BRACKET("RIGHT_BRACKET"),
    DOUBLE_QUOTES("DOUBLE_QUOTES"), 
    COLON("COLON"),
    STRING("STRING"),
    COMMA("COMMA");

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

  public JsonToken(TokenType tokenType) {
    this.tokenType = tokenType;
    if(tokenType == STRING) {
      throw new LexerReadError("Token type of String needs a value associated with it.");
    }
    if(tokenType == LEFT_BRACKET) {
        this.value = "["; 
    }
    if(tokenType == RIGHT_BRACKET) {
      this.value = "]";
    }
    if(tokenType == COMMA) {
      this.value = ",";
    }
    if(tokenType == DOUBLE_QUOTES) {
      this.value = "\""; 
    }
    if(tokenType == LEFT_CURLY_BRACKET) {
      this.value = "{"; 
    }
    if(tokenType == RIGHT_CURLY_BRACKET) {
      this.value = "}"; 
    }
    if(tokenType == COLON) {
      this.value = ":";
    }
  
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
