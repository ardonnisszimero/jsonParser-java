import static com.parser.JsonToken.TokenType.DOUBLE_QUOTES;
import static com.parser.JsonToken.TokenType.LEFT_CURLY_BRACKET;
import static com.parser.JsonToken.TokenType.RIGHT_CURLY_BRACKET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.parser.JsonParserError;
import com.parser.JsonToken;
import com.parser.Parser;

public class PaserTest {
    private Parser parser; 

    @Test
    public void validationTest_returnsTrue() {
        List<JsonToken> tokens = List.of(
            new JsonToken(LEFT_CURLY_BRACKET), 
            new JsonToken(RIGHT_CURLY_BRACKET)
        );        
        parser =  new Parser(tokens);
        boolean consumed = parser.validate(); 
        assertEquals(true, consumed);
    }

    @Test
    public void validationTest_returnFalse() {
        List<JsonToken> tokens = List.of(
            new JsonToken(LEFT_CURLY_BRACKET), 
            new JsonToken(LEFT_CURLY_BRACKET)
        );        
        parser =  new Parser(tokens);
        boolean consumed = parser.validate(); 
        assertEquals(false, consumed);
    }

    @Test 
    public void validateTest_commaError() {
        //{"key": "value",}
        List<JsonToken> tokens = List.of(
            new JsonToken(LEFT_CURLY_BRACKET), 
            new JsonToken(DOUBLE_QUOTES), 
            new JsonToken(RIGHT_CURLY_BRACKET)
        );
        Parser parser = new Parser(tokens); 
        assertThrows(JsonParserError.class, ()-> {
        
        },"Expected JsonPaserError to be thrown.");
    }
}
