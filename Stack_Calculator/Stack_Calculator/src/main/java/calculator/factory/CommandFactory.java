package calculator.factory;

import calculator.command.Command;
import calculator.exception.CalculatorIOException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.UnknownCommandRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private final Properties commandProperties = new Properties();

    public CommandFactory() throws CalculatorIOException {
        logger.info("Create object of factory");
        try (InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("commands.properties")) {
            if (input == null) {
                throw new CalculatorIOException("Файл commands.properties не найден!");
            }
            commandProperties.load(input);
            logger.info("Loaded commands.properties successfully");
        } catch (NullPointerException | IOException | CalculatorIOException e) {
            throw new CalculatorIOException("Ошибка загрузки commands.properties: " + e.getMessage());
        }
    }

    public Command createCommand(String commandName) {
        if (commandName == null) {
            throw new InvalidArgumentsRuntimeException("Command name is null");
        }
        logger.info("Create command: {}", commandName);
        String className = commandProperties.getProperty(commandName);
        if (className == null) {
            throw new InvalidArgumentsRuntimeException("Unknown command: " + commandName);
        }

        try {
            Class<?> commandClass = Class.forName(className);
            Command commandObject = (Command) commandClass.getDeclaredConstructor().newInstance();
            logger.info("Command {} created successfully", commandName);
            return commandObject;
        } catch (Exception e) {
            throw new UnknownCommandRuntimeException("Error creating command '" + commandName + "': " + e.getMessage());
        }
    }
}