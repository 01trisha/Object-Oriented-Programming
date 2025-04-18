package calculator.command;


import calculator.context.ExecutableContex;
import calculator.exception.DivisionByNullRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DivCommandTest {
    private DivCommand divCommand;

    @Mock
    private ExecutableContex context;

    @BeforeEach
    void setup(){
        divCommand = new DivCommand();
    }

    @Test
    void DivCommandSuccessTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(5.0, 10.0);

        assertDoesNotThrow(() -> divCommand.execute(context, new String[0]));

        verify(context, times(2)).pop();
        verify(context).push(2.0);
    }

    @Test
    void DivCommandEmptyStackTest(){
        when(context.size()).thenReturn(1);

        StackUnderflowRuntimeException exception = assertThrows(StackUnderflowRuntimeException.class, () -> divCommand.execute(context, new String[0]));

        assertEquals("Stack has not enough arguments for DIV", exception.getMessage());
    }

    @Test
    void DivCommandInvalidArgumentsTest(){
        String[] args = {"qwerty"};

        InvalidArgumentsRuntimeException exception = assertThrows(InvalidArgumentsRuntimeException.class, () -> divCommand.execute(context, args));

        assertEquals("DIV doesn't need arguments", exception.getMessage());
    }


    @Test
    void DivCommandByZeroTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(0.0, 1.0);

        DivisionByNullRuntimeException exception = assertThrows(DivisionByNullRuntimeException.class, () -> divCommand.execute(context, new String[0]));

        assertEquals("You can't divide 1.0 by 0", exception.getMessage());
    }
}
