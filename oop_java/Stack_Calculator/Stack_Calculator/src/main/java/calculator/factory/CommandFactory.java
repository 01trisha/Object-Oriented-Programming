package calculator.factory;

import calculator.command.Command;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommandFactory {
    private final Properties commandProperties = new Properties();

    public CommandFactory(){

    }
}




























//
//public class CommandFactory {
//    private final Properties commandProperties = new Properties();
//
//    public CommandFactory() {
//        try(InputStream in = CommandFactory.class.getResourceAsStream("commands.properties")) {
//            commandProperties.load(in);
//            if (in == null) {
//                throw new IllegalStateException("Файл commands.properties не найден");
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("Ошибка чтения файла " +e);
//        }
//    }
//
//    public Command createCommand(String commandName){
//        String className = commandProperties.getProperty(commandName);
//
//        if (className == null){
//            throw new IllegalArgumentException("Неизвестная команда " + commandName);
//        }
//
//        try{
//            Class<?> commandClass = Class.forName(className);
//            return (Command) commandClass.getDeclaredConstructor().newInstance();
//        }catch(Exception e){
//            throw new RuntimeException("Ошибка создания команды " + commandName, e);
//        }
//    }
//}
