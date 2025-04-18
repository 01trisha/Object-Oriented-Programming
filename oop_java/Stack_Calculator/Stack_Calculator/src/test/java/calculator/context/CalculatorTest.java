package calculator.context;

import calculator.command.Command;
import calculator.exception.CalculatorIOException;
import calculator.factory.CommandFactory;
import calculator.utils.InputReader;
import calculator.utils.ParsedArgs;
import calculator.utils.ParserLines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class CalculatorTest {

    private CommandFactory factory;
    private ExecutableContex context;
    private ParserLines parser;
    private Command command;
    private ParsedArgs parsedArgs;

    @BeforeEach
    void setUp() {
        factory = mock(CommandFactory.class);
        context = mock(ExecutableContex.class);
        parser = mock(ParserLines.class);
        command = mock(Command.class);
        parsedArgs = new ParsedArgs("PUSH", new String[]{"10"});
    }

    @Test
    void testRunWithSingleCommandFromStdin() throws Exception {
        String simulatedInput = "PUSH 10\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        Calculator calculator = spy(new Calculator(factory, context, parser));

        doReturn(new InputReader() {
            final Scanner fakeScanner = scanner;

            @Override
            public String readLine() {
                return fakeScanner.hasNextLine() ? fakeScanner.nextLine() : null;
            }

            @Override
            public void close() {
                fakeScanner.close();
            }
        }).when(calculator).createReader(new String[0]);

        // Подготавливаем поведение мока
        when(parser.parse("PUSH 10")).thenReturn(parsedArgs);
        when(factory.createCommand("PUSH")).thenReturn(command);

        // Выполняем
        calculator.run(new String[0]);

        // Проверки
        verify(factory).createCommand("PUSH");
        verify(command).execute(context, new String[]{"10"});
    }
}
