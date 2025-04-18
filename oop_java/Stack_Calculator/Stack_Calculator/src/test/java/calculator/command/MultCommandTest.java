package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MultCommandTest {
    private MultCommand multCommand;

    @Mock
    private ExecutableContex context;

    @BeforeEach
    void setup(){
        multCommand = new MultCommand();
    }

    @Test
    void testMultInvalidArgs() {
        String[] args = {"asdf"};
        InvalidArgumentsRuntimeException check = assertThrows(InvalidArgumentsRuntimeException.class, () -> multCommand.execute(context, args));
        assertEquals("MULT doesn't need arguments", check.getMessage());
    }

    @Test
    void testMultEmptyStack(){
        when(context.size()).thenReturn(1);
        StackUnderflowRuntimeException check = assertThrows(StackUnderflowRuntimeException.class, () -> multCommand.execute(context, new String[0]));
        assertEquals("Stack has not enough arguments for MULT", check.getMessage());
    }

    @Test
    void testMultAllCorrectly(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(3.0, 4.0);

        multCommand.execute(context, new String[]{});
        verify(context).push(12.0);
        verify(context, times(2)).pop();
    }

}
