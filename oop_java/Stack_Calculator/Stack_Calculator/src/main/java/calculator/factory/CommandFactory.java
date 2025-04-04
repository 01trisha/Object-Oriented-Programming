package calculator.factory;

import calculator.command.Command;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    private final Properties commandProperties = new Properties();

    public CommandFactory() {
        try (InputStream input = CommandFactory.class.getResourceAsStream("/commands.properties")) {
            if (input == null) {
                throw new CalculatorException("Файл commands.properties не найден!");
            }
            commandProperties.load(input);
        } catch (IOException | CalculatorException e) {
            throw new RuntimeException("Ошибка загрузки commands.properties: " + e.getMessage(), e);
        }
    }

    public Command createCommand(String commandName) throws CalculatorException {
        String className = commandProperties.getProperty(commandName);
        if (className == null) {
            throw new InvalidArgumentsException("Неизвестная команда: " + commandName);
        }

        try {
            Class<?> commandClass = Class.forName(className);
            return (Command) commandClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new CalculatorException("Ошибка создания команды '" + commandName + "': " + e.getMessage(), e);
        }
    }
}
