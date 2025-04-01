package calculator.factory;

import calculator.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    private final Properties commandProperties = new Properties();

    public CommandFactory() {
        try (InputStream input = CommandFactory.class.getResourceAsStream("commands.properties")) {
            commandProperties.load(input);
            if (commandProperties == null) {
                throw new IllegalStateException("Ошибка чтения файла commands.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла commands.properties", e);

        }
    }

    public Command createCommand(String commandName) {
        String className = commandProperties.getProperty(commandName);

        if (className == null){
            throw new IllegalArgumentException("Неизвестная команда " + commandName);
        }

        try{
            Class<?> commandClass = Class.forName(className);
            Command newCommand = (Command) commandClass.getDeclaredConstructor().newInstance();
            return newCommand;
        }catch(Exception e){
            throw new RuntimeException("Ошибка создания новой команды" + e);
        }
    }
}

