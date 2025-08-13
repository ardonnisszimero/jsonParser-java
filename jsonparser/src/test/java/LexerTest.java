import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.parser.JsonToken;
import com.parser.JsonToken.TokenType;
import com.parser.Lexer;

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
            new JsonToken(TokenType.LEFT_BRACKET, "{"), 
            new JsonToken(TokenType.RIGHT_BRACKET, "}")
        ); 
        String jsonString = "{}";
        List<JsonToken> consumed = lexer.tokenize(jsonString); 
        
        assertEquals(expected, consumed);
    }
}
