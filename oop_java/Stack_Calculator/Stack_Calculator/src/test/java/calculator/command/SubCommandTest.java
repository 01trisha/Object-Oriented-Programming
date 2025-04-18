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
class SubCommandTest{

    @Mock
    private ExecutableContex context;

    private SubCommand subCommand;

    @BeforeEach
    void setUp(){
        subCommand = new SubCommand();
    }

    @Test
    void SubCommandSuccessTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(3.0).thenReturn(5.0);

        subCommand.execute(context, new String[]{});

        verify(context).push(2.0);
    }

    @Test
    void SubCommandNegativeResultTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(5.0).thenReturn(3.0);

        subCommand.execute(context, new String[]{});

        verify(context).push(-2.0);
    }

    @Test
    void SubCommandWithZeroTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(0.0).thenReturn(5.0);

        subCommand.execute(context, new String[]{});

        verify(context).push(5.0);
    }

    @Test
    void SubCommandInvalidArgsTest(){
        String[] args ={"arg"};

        InvalidArgumentsRuntimeException exception = assertThrows(
                InvalidArgumentsRuntimeException.class,
                () -> subCommand.execute(context, args)
        );

        assertEquals("SUB doesn't need arguments", exception.getMessage());
    }

    @Test
    void SubCommandWithEmptyStackTest(){
        when(context.size()).thenReturn(0);

        StackUnderflowRuntimeException exception = assertThrows(
                StackUnderflowRuntimeException.class,
                () -> subCommand.execute(context, new String[]{})
        );

        assertEquals("Stack has not enough arguments for SUB", exception.getMessage());
    }

    @Test
    void SubCommandWithOneArgumentTest(){
        when(context.size()).thenReturn(1);

        StackUnderflowRuntimeException exception = assertThrows(
                StackUnderflowRuntimeException.class,
                () -> subCommand.execute(context, new String[]{})
        );

        assertEquals("Stack has not enough arguments for SUB", exception.getMessage());
    }

    @Test
    void SubCommandCheckCountPopTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(1.0).thenReturn(2.0);

        subCommand.execute(context, new String[]{});

        verify(context, times(2)).pop();
    }

    @Test
    void SubCommandCheckCountPushTest(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(1.0).thenReturn(2.0);

        subCommand.execute(context, new String[]{});

        verify(context, times(1)).push(1.0); // 2 - 1 = 1
    }
}