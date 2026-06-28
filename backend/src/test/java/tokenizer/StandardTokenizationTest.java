package tokenizer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StandardTokenizationTest {
    @Test
    void basicSentenceTest() {
        var tokenizer = new StandardTokenization();
        assertEquals(List.of("test"), tokenizer.toTokens("This, is a test!"));
    }

    @Test
    void InputEmptyTest() {
        var tokenizer = new StandardTokenization();
        assertEquals(List.of(), tokenizer.toTokens(""));
        assertEquals(List.of(), tokenizer.toTokens(null));
    }

}