package com.parser; 


public class LexerReadError extends RuntimeException {
    public LexerReadError(String msg) {
        super(msg);
    }
}