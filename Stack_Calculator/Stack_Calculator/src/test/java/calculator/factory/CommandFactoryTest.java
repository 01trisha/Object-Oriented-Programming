package calculator.factory;

import calculator.command.Command;
import calculator.command.PushCommand;
import calculator.context.ExecutableContex;
import calculator.exception.CalculatorIOException;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.UnknownCommandRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {

    private CommandFactory factory;

    @BeforeEach
    void setUp() throws CalculatorIOException {
        factory = new CommandFactory();
    }
    @Test
    void testCreateValidCommand() throws Exception {
        Command command = factory.createCommand("PUSH");
        
        assertNotNull(command);
        assertInstanceOf(PushCommand.class, command);
    }

    @Test
    void testCreateInvalidCommand() throws Exception {
        assertThrows(InvalidArgumentsRuntimeException.class,
            () -> factory.createCommand("INVALID_COMMAND"));
    }

    @Test
    void testCreateNullCommand() throws Exception {

        assertThrows(InvalidArgumentsRuntimeException.class,
            () -> factory.createCommand(null));
    }

    @Test
    void testCreateEmptyCommand() throws Exception {
        assertThrows(InvalidArgumentsRuntimeException.class,
            () -> factory.createCommand(""));
    }
}
