import static com.parser.JsonToken.TokenType.COLON;
import static com.parser.JsonToken.TokenType.COMMA;
import static com.parser.JsonToken.TokenType.DOUBLE_QUOTES;
import static com.parser.JsonToken.TokenType.LEFT_CURLY_BRACKET;
import static com.parser.JsonToken.TokenType.RIGHT_CURLY_BRACKET;
import static com.parser.JsonToken.TokenType.STRING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.parser.JsonToken;
import com.parser.Lexer;
import com.parser.LexerReadError;

public class LexerTest {
    private final Lexer lexer = new Lexer(); 
    
    @Test
    public void openFileReader_sucess() {
        String consumed = lexer.getJsonFileAsString(
            "/home/ardy/Dev/java/jsonParser-java/jsonparser/src/main/resources/tests/step1/valid.json"
        );
        String expected = "{}";
        assertEquals(expected, consumed);
    }

    @Test 
    public void tokenize_basic() {
        List<JsonToken> expected = List.of(
            new JsonToken(LEFT_CURLY_BRACKET),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(RIGHT_CURLY_BRACKET)
        ); 
        String jsonString = "{\"\"}";
        List<JsonToken> consumed = lexer.tokenize(jsonString); 
        
        assertEquals(expected, consumed);
    }

    @Test
    public void tokenize_withObject() {
        List<JsonToken> expected = List.of(
            new JsonToken(LEFT_CURLY_BRACKET),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "key"), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(COLON), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "value"),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(RIGHT_CURLY_BRACKET)
        );
        List<JsonToken> expected2 = List.of(
            new JsonToken(LEFT_CURLY_BRACKET),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "key"), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(COLON), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "value"),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(COMMA),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "key2"), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(COLON), 
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(STRING, "value2"),
            new JsonToken(DOUBLE_QUOTES),
            new JsonToken(RIGHT_CURLY_BRACKET)
        );
        String jsonString = "{\"key\":\"value\"}";
        String jsonString2 = "{\"key\":\"value\",\"key2\":\"value2\"}";
        List<JsonToken> consumed = lexer.tokenize(jsonString); 
        List<JsonToken> consumed2 = lexer.tokenize(jsonString2);

        assertEquals(expected, consumed);
        assertEquals(expected2, consumed2);
    }

    @Test 
    public void tokenize_withObject_invalid() {
        String invalidJsonString = "{\"key\": \"value\"}";
        LexerReadError thrown = assertThrows(
                LexerReadError.class, 
                () -> lexer.tokenize(invalidJsonString)
        );
        assertTrue(thrown.getMessage().contains("Invalid Character"));
    }
}