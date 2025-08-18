package com.parser;

import static com.parser.JsonToken.TokenType.COMMA;
import static com.parser.JsonToken.TokenType.LEFT_BRACKET;
import static com.parser.JsonToken.TokenType.RIGHT_BRACKET;
import static com.parser.JsonToken.TokenType.STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    List<JsonToken> tokens = new ArrayList<>();

    public Parser(List<JsonToken> jsonTokens) {
        this.tokens = jsonTokens;
    }

    public boolean validate() {
        Stack<JsonToken> braces = new Stack<>(); 
        for(int i = 0; i < tokens.size(); i++) {
            JsonToken token = tokens.get(i);
            if(token.getTokenType() == LEFT_BRACKET) {
                braces.add(token);
            } 
            else if(token.getTokenType() == RIGHT_BRACKET) {
                braces.pop();
            } 
            else if(token.getTokenType() == COMMA) {
                if(i + 1 < tokens.size()) {
                    JsonToken nextToken =  tokens.get(i);
                    if(nextToken.getTokenType() != STRING || nextToken.getTokenType() != RIGHT_BRACKET) {
                        throw new JsonParserError("Invalid Token after C");
                    }
                }
            }
        }
        return braces.empty();

    }
}
