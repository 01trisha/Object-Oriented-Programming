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
class SqrtCommandTest{

    @Mock
    private ExecutableContex context;

    private SqrtCommand sqrtCommand;

    @BeforeEach
    void setUp(){
        sqrtCommand = new SqrtCommand();
    }

    @Test
    void SqrtCommandSuccessTest(){
        when(context.size()).thenReturn(1);
        when(context.pop()).thenReturn(16.0);

        sqrtCommand.execute(context, new String[]{});

        verify(context).push(4.0);
    }

    @Test
    void SqrtCommandSuccessWithZeroTest() throws CalculatorRuntimeException{
        when(context.size()).thenReturn(1);
        when(context.pop()).thenReturn(0.0);

        sqrtCommand.execute(context, new String[]{});

        verify(context).push(0.0);
    }

    @Test
    void SqrtCommandInvalidArgsTest(){
        String[] args ={"arg"};

        InvalidArgumentsRuntimeException exception = assertThrows(
                InvalidArgumentsRuntimeException.class,
                () -> sqrtCommand.execute(context, args)
        );

        assertEquals("SQRT doesn't need arguments", exception.getMessage());
    }

    @Test
    void SqrtCommandWithEmptyStackTest(){
        when(context.size()).thenReturn(0);

        StackUnderflowRuntimeException exception = assertThrows(
                StackUnderflowRuntimeException.class,
                () -> sqrtCommand.execute(context, new String[]{})
        );

        assertEquals("Stack has not enough arguments for SQRT", exception.getMessage());
    }

    @Test
    void SqrtCommandNegativeArgsTest(){
        when(context.size()).thenReturn(1);
        when(context.pop()).thenReturn(-4.0);

        NegativeValueForSqrtRuntimeException exception = assertThrows(
                NegativeValueForSqrtRuntimeException.class,
                () -> sqrtCommand.execute(context, new String[]{})
        );

        assertEquals("-4.0 is negative value", exception.getMessage());
    }

    @Test
    void SqrtCommandCheckCountPopTest(){
        when(context.size()).thenReturn(1);
        when(context.pop()).thenReturn(9.0);

        sqrtCommand.execute(context, new String[]{});

        verify(context, times(1)).pop();
    }

    @Test
    void SqrtCommandCheckCountPushTest(){
        when(context.size()).thenReturn(1);
        when(context.pop()).thenReturn(25.0);

        sqrtCommand.execute(context, new String[]{});

        verify(context, times(1)).push(5.0);
    }
}