package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddCommandTest {
    private AddCommand addCommand;

    @Mock
    private ExecutableContex context;

    @BeforeEach
    void setup(){
        addCommand = new AddCommand();
    }

    @Test
    @DisplayName("Проверка + на исполнение с аргументами")
    void AddCommandInvalidArgumentsTest(){
        String[] args = {"qwerty"};

        InvalidArgumentsRuntimeException exception = assertThrows(InvalidArgumentsRuntimeException.class, () -> addCommand.execute(context, args));

        assertEquals("AddCommand doesn't need arguments", exception.getMessage());

        verify(context, never()).pop();
    }

    @Test
    @DisplayName("Проверка + на исполнение с пустым стеком")
    void AddCommandEmptyStackTest(){
        when(context.size()).thenReturn(1);

        StackUnderflowRuntimeException exception = assertThrows(StackUnderflowRuntimeException.class, () -> addCommand.execute(context, new String[0]));

        assertEquals("Stack has not enough arguments for ADD", exception.getMessage());
    }

    @Test
    @DisplayName("Проверка + на нормальное исполнение")
    void AddCommandSuccess(){
        when(context.size()).thenReturn(2);
        when(context.pop()).thenReturn(2.0, 3.0);

        assertDoesNotThrow(() -> addCommand.execute(context, new String[0]));
        verify(context, times(2)).pop();
        verify(context).push(5.0);
    }

}
