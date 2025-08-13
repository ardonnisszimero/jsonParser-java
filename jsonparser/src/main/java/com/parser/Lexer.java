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
        return content.toString();
    }

    public List<JsonToken> tokenize(String jsonString) {
        List<JsonToken> tokens = new ArrayList<>(); 
        for(Character c : jsonString.toCharArray()) {
            switch(c) {
                case '{': 
                    tokens.add(new JsonToken(TokenType.LEFT_BRACKET, Character.toString(c))); 
                    break;
                case '}': 
                    tokens.add(new JsonToken(TokenType.RIGHT_BRACKET, Character.toString(c)));
                    break; 

                default:
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
