package calculator.command;

import calculator.context.ExecutableContex;
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
public class PopCommandTest {

    private PopCommand popCommand;
    @Mock
    private ExecutableContex context;

    @BeforeEach
    void setUp() {
        popCommand = new PopCommand();
    }

    @Test
    void testPopCommandSuccess() {
        when(context.size()).thenReturn(1);

        assertDoesNotThrow(() -> popCommand.execute(context, new String[0]));
        verify(context, times(1)).pop();
    }

    @Test
    void testPopCommandWithArgumentsThrows() {
        String[] args = {"aaa"};

        InvalidArgumentsRuntimeException exception = assertThrows(
                InvalidArgumentsRuntimeException.class,
                () -> popCommand.execute(context, args)
        );
        assertEquals("POP doesn't need arguments", exception.getMessage());

        verify(context, never()).pop();
    }

    @Test
    void testPopCommandWithEmptyStackThrows() {
        when(context.size()).thenReturn(0);

        StackUnderflowRuntimeException exception = assertThrows(
                StackUnderflowRuntimeException.class,
                () -> popCommand.execute(context, new String[0])
        );
        assertEquals("Stack has not enough arguments for POP", exception.getMessage());

        verify(context, never()).pop();
    }
}
