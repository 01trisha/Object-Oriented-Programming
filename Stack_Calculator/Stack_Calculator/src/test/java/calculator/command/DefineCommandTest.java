package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.InvalidVariableNameRuntimeException;
import calculator.exception.NotANumberRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DefineCommandTest {
    private DefineCommand devineCommand;

    @Mock
    private ExecutableContex context;

    @BeforeEach
    void setup(){
        devineCommand = new DefineCommand();
    }

    @Test
    void DefineCommandSuccessTest(){
        String[] args = {"a", "10"};

        assertDoesNotThrow(() -> devineCommand.execute(context, args));
        verify(context, times(1)).put("a", 10);
    }

    @Test
    void DefineCommandInvalidArgumentsTest(){
        String[] args1 = {"one args"};
        String[] args2 = {"to", "many", "args"};

        assertAll( () -> assertThrows(InvalidArgumentsRuntimeException.class, () -> devineCommand.execute(context, args1)),
                () -> assertThrows(InvalidArgumentsRuntimeException.class, () -> devineCommand.execute(context, args2)));
    }

    @Test
    void DefineCommandInvalidVariableName_NotANumberTest(){
        String[] args1 = {"10", "12"};
        String[] args2 = {"a", "b"};
        assertAll( () -> assertThrows(InvalidVariableNameRuntimeException.class, () -> devineCommand.execute(context, args1)),
                () -> assertThrows(NotANumberRuntimeException.class, () -> devineCommand.execute(context, args2)));
    }

    @Test
    void isNumber_ShouldCorrectlyIdentifyNumbers() {
        assertAll(
                () -> assertTrue(devineCommand.isNumber("123")),
                () -> assertTrue(devineCommand.isNumber("3.14")),
                () -> assertTrue(devineCommand.isNumber("-42.5")),
                () -> assertFalse(devineCommand.isNumber("abc")),
                () -> assertFalse(devineCommand.isNumber("123abc")),
                () -> assertFalse(devineCommand.isNumber(""))
        );
    }
}
