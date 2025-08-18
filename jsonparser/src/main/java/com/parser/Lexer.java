package com.parser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.parser.JsonToken.TokenType;

public class Lexer {
    public static final Logger LOG = LogManager.getLogger(Lexer.class);

    List<JsonToken> jsonToken = new ArrayList<>();
    FileReader fileReader;

   
    public Lexer() {
        
    }

    public String getJsonFileAsString(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            this.fileReader = new FileReader(fileName);
            while(fileReader.ready()) {
                char c = (char) fileReader.read();
                content.append(Character.toString(c));
            }
        } catch(Exception ex) {
            LOG.error("Unable to open JSON file.", ex);
        }
        return new String(content).replace(" ", "").replace("\n", "");

    }

    public List<JsonToken> tokenize(String jsonString) {
List<JsonToken> tokens = new ArrayList<>(); 
        for(int i = 0; i < jsonString.length(); i++) {
            char c = jsonString.charAt(i);
            int n = jsonString.length();
            switch(c) {
                case '{': 
                    tokens.add(new JsonToken(TokenType.LEFT_CURLY_BRACKET)); 
                    break;
                case '}': 
                    tokens.add(new JsonToken(TokenType.RIGHT_CURLY_BRACKET));
                    break;   
                case ':': 
                    tokens.add(new JsonToken(TokenType.COLON)); 
                    break;
                case ',': 
                    tokens.add(new JsonToken(TokenType.COMMA)); 
                    break;
                case '[': 
                    tokens.add(new JsonToken(TokenType.LEFT_BRACKET)); 
                    break;
                case ']': 
                    tokens.add(new JsonToken(TokenType.RIGHT_BRACKET)); 
                    break; 
                case '\"': 
                    tokens.add(new JsonToken(TokenType.DOUBLE_QUOTES)); 
                    StringBuilder currentString = new StringBuilder();
                    i++;
                    char inQuotesC = jsonString.charAt(i);
                    while(inQuotesC != '\"' && i < n) {
                        currentString.append(inQuotesC); 
                        i++;
                        inQuotesC = jsonString.charAt(i);
                    }
                    if(inQuotesC == '\"') {
                        if(!currentString.isEmpty()) {
                            tokens.add(new JsonToken(TokenType.STRING, new String(currentString.toString())));
                        }
                        tokens.add(new JsonToken(TokenType.DOUBLE_QUOTES));
                    } else if(inQuotesC != '\"') {
                        throw new LexerReadError("Expected ending Double Quotes but got " +  inQuotesC);
                    } else {
                        throw new LexerReadError("Expected Double Quotes but reached end of file.");
                    }
                    break;
                default:
                    throw new LexerReadError("Invalid Character: " + c  +  ". Expected {, [, ',', or \" ");
            }
        }

        return tokens;
    }

    public void cleanUp() {
        try {
            fileReader.close();
        } catch (Exception ex) {
            LOG.error("Couldn't close current JSON file.", ex);
        }
    }
}
