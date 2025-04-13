package calculator.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ParserLinesTest {
    private ParserLines parser;

    @BeforeEach
    void setUp() {
        parser = new ParserLines();
    }

    @Test
    void testParseValidLine() {
        String input = "PUSH 5";
        String expected = "PUSH";
        String result = parser.parse(input).getCommand();

        assertEquals(expected, result);
    }

    @Test
    void testParseEmptyLine() {
        String input = "";
        String[] result = parser.parse(input).getArgs();

        assertEquals(0, result.length);
    }

    @Test
    void testParseLineWithExtraSpaces() {
        String input = "   PRINT    ";
        String[] expected = {"PRINT"};
        String[] result = parser.parse(input).getArgs();

        assertArrayEquals(expected, result);
    }
}