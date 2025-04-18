package calculator.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParsedArgsTest {

    @Test
    void testGetCommandAndArgs() {
        String command = "PUSH";
        String[] args = {"5"};

        ParsedArgs parsedArgs = new ParsedArgs(command, args);

        assertEquals("PUSH", parsedArgs.getCommand());
        assertArrayEquals(new String[]{"5"}, parsedArgs.getArgs());
    }

    @Test
    void testEmptyArgs() {
        String command = "POP";
        String[] args = {};

        ParsedArgs parsedArgs = new ParsedArgs(command, args);

        assertEquals("POP", parsedArgs.getCommand());
        assertArrayEquals(new String[]{}, parsedArgs.getArgs());
    }

    @Test
    void testNullArgs() {
        String command = "PRINT";
        ParsedArgs parsedArgs = new ParsedArgs(command, null);

        assertEquals("PRINT", parsedArgs.getCommand());
        assertNull(parsedArgs.getArgs());
    }
}
