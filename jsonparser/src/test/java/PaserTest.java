import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.parser.JsonToken;
import com.parser.JsonToken.TokenType;
import com.parser.Parser;

public class PaserTest {
    private Parser parser; 

    @Test
    public void validationTest_returnsTrue() {
        List<JsonToken> tokens = List.of(
            new JsonToken(TokenType.LEFT_BRACKET, "{"), 
            new JsonToken(TokenType.RIGHT_BRACKET, "}")
        );        
        parser =  new Parser(tokens);
        boolean consumed = parser.validate(); 
        assertEquals(true, consumed);
    }

    @Test
    public void validationTest_returnFalse() {
        List<JsonToken> tokens = List.of(
            new JsonToken(TokenType.LEFT_BRACKET, "{"), 
            new JsonToken(TokenType.LEFT_BRACKET, "{")
        );        
        parser =  new Parser(tokens);
        boolean consumed = parser.validate(); 
        assertEquals(false, consumed);
    }
}
