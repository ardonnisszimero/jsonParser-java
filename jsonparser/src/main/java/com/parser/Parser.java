package com.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.parser.JsonToken.TokenType;

public class Parser {
    List<JsonToken> tokens = new ArrayList<>();

    public Parser(List<JsonToken> jsonTokens) {
        this.tokens = jsonTokens;
    }

    public boolean validate() {
        Stack<JsonToken> braces = new Stack<>(); 
        for(JsonToken token : tokens) {
            if(token.getTokenType() == TokenType.LEFT_BRACKET) braces.add(token); 
            else if(token.getTokenType() == TokenType.RIGHT_BRACKET) braces.pop(); 
        }
        return braces.empty();

    }
}
