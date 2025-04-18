package calculator.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserLinesTest {

    private ParserLines parserLines;

    @BeforeEach
    void setUp() {
        parserLines = new ParserLines();
    }

    @Test
    void testParseCommandWithArgs() {
        String input = "PUSH 10";
        ParsedArgs result = parserLines.parse(input);

        assertNotNull(result);
        assertEquals("PUSH", result.getCommand());
        assertArrayEquals(new String[]{"10"}, result.getArgs());
    }

    @Test
    void testParseCommandWithoutArgs() {
        String input = "POP";
        ParsedArgs result = parserLines.parse(input);

        assertNotNull(result);
        assertEquals("POP", result.getCommand());
        assertArrayEquals(new String[]{}, result.getArgs());
    }

    @Test
    void testParseNullLineReturnsNull() {
        assertNull(parserLines.parse(null));
    }

    @Test
    void testParseEmptyLineReturnsNull() {
        assertNull(parserLines.parse(""));
    }

    @Test
    void testParseLineWithOnlySpacesReturnsNull() {
        assertNull(parserLines.parse("    "));
    }

    @Test
    void testParseWithMultipleSpacesBetweenArguments() {
        String input = "DEFINE     x     15";
        ParsedArgs result = parserLines.parse(input);

        assertNotNull(result);
        assertEquals("DEFINE", result.getCommand());
        assertArrayEquals(new String[]{"", "", "", "", "x", "", "", "", "", "15"}, result.getArgs());
    }
}
