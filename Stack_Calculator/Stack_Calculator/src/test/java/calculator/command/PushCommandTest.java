package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PushCommandTest {

    @Mock
    private ExecutableContex context;

    private PushCommand pushCommand;

    @BeforeEach
    void setUp() {
        pushCommand = new PushCommand();
    }

    @Test
    void PushCommandSuccessTest(){
        String[] args1 = {"42.5"};
        String[] args2 = {"-10.3"};

        pushCommand.execute(context, args1);
        verify(context).push(42.5);

        pushCommand.execute(context, args2);
        verify(context).push(-10.3);
    }

    @Test
    void PushCommandPushVarTest(){
        String[] args = {"var1"};
        when(context.get("var1")).thenReturn(3.14);

        pushCommand.execute(context, args);
        verify(context).push(3.14);
    }

    @Test
    void PushCommandPushUnknownVarTest(){
        String[] args = {"unknownVar"};
        when(context.get("unknownVar")).thenThrow(new UnknownVariableRuntimeException("Not found"));

        CalculatorRuntimeException exception = assertThrows(CalculatorRuntimeException.class,
                () -> pushCommand.execute(context, args));

        assertEquals("Unknown variable name: unknownVar", exception.getMessage());
    }

    @Test
    void PushCommandWithEmptyArgsTest(){
        String[] args = {};

        InvalidArgumentsRuntimeException exception = assertThrows(InvalidArgumentsRuntimeException.class,
                () -> pushCommand.execute(context, args));

        assertEquals("PUSH needs 1 arguments", exception.getMessage());
    }

    @Test
    void PushCommandInvalidArgumentsTest() {
        String[] args = {"a", "b"};

        InvalidArgumentsRuntimeException exception = assertThrows(InvalidArgumentsRuntimeException.class,
                () -> pushCommand.execute(context, args));

        assertEquals("PUSH needs 1 arguments", exception.getMessage());
    }

    @Test
    void PushCommandArgsIsNumberTest() {
        assertAll(
                () -> assertTrue(pushCommand.isNumber("123")),
                () -> assertTrue(pushCommand.isNumber("3.14")),
                () -> assertTrue(pushCommand.isNumber("-42.5")),
                () -> assertTrue(pushCommand.isNumber("0.0")),
                () -> assertTrue(pushCommand.isNumber("1e-10")),
                () -> assertFalse(pushCommand.isNumber("abc")),
                () -> assertFalse(pushCommand.isNumber("123abc")),
                () -> assertFalse(pushCommand.isNumber("")),
                () -> assertFalse(pushCommand.isNumber(" ")),
                () -> assertFalse(pushCommand.isNumber("1.2.3"))
        );
    }

}