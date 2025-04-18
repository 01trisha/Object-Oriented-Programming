package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrintCommandTest{

    @Mock
    private ExecutableContex context;

    private PrintCommand printCommand;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp(){
        printCommand = new PrintCommand();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void PrintCommandSuccessTest(){
        when(context.size()).thenReturn(1);
        when(context.peek()).thenReturn(42.5);

        printCommand.execute(context, new String[]{});

        assertEquals("42.5" + System.lineSeparator(), outputStream.toString());
        verify(context).peek();
    }

    @Test
    void PrintCommandWithZeroTest(){
        when(context.size()).thenReturn(1);
        when(context.peek()).thenReturn(0.0);

        printCommand.execute(context, new String[]{});

        assertEquals("0.0" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void PrintCommandWithNegativeValueTest(){
        when(context.size()).thenReturn(1);
        when(context.peek()).thenReturn(-10.3);

        printCommand.execute(context, new String[]{});

        assertEquals("-10.3" + System.lineSeparator(), outputStream.toString());
    }

    @Test
    void PrintCommandInvalidArgsTest(){
        String[] args ={"arg"};

        InvalidArgumentsRuntimeException exception = assertThrows(
                InvalidArgumentsRuntimeException.class,
                () -> printCommand.execute(context, args)
        );

        assertEquals("PRINT doesn't need arguments", exception.getMessage());
    }

    @Test
    void PrintCommandWithEmptyStackTest(){
        when(context.size()).thenReturn(0);

        StackUnderflowRuntimeException exception = assertThrows(
                StackUnderflowRuntimeException.class,
                () -> printCommand.execute(context, new String[]{})
        );

        assertEquals("Stack has not enough arguments for PRINT", exception.getMessage());
    }

    @Test
    void PrintCommandCheckPeekCalledTest(){
        when(context.size()).thenReturn(1);
        when(context.peek()).thenReturn(100.0);

        printCommand.execute(context, new String[]{});

        verify(context, times(1)).peek();
    }

    @Test
    void PrintCommandNoPopOperationTest(){
        when(context.size()).thenReturn(1);
        when(context.peek()).thenReturn(50.0);

        printCommand.execute(context, new String[]{});

        verify(context, never()).pop();
    }
}