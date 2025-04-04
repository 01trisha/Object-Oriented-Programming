package calculator.context;

import calculator.command.Command;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.factory.CommandFactory;
import calculator.utils.InputReader;
import calculator.utils.ParsedArgs;
import calculator.utils.ParserLines;

import java.io.IOException;

public class Calculator {
    private final CommandFactory factory;
    private final ExecutableContex context;
    private final ParserLines parser;

    public Calculator(CommandFactory factory, ExecutableContex context, ParserLines parser) {
        this.factory = factory;
        this.context = context;
        this.parser = parser;
    }

    public void run(String[] args) throws CalculatorException {
        try(InputReader reader = createReader(args)){
            String line;
            while((line = reader.readLine()) != null){
                try{
                    ParsedArgs parsedArgs = parser.parse(line);
                    if (parsedArgs == null){
                        continue;
                    }
                    executeCommand(parsedArgs);
                }catch (CalculatorException e){
                    System.err.println("Error of executing command: " + e.getMessage());
//                    if (e.getCause() != null) {
//                        System.err.println("Caused by: " + e.getCause().getMessage());
//                    }

                }
            }
        }catch (InvalidArgumentsException e){
            System.err.println(e.getMessage());
        }

    }

    private InputReader createReader(String[] args) throws CalculatorException {
        if (args.length > 1){
            throw new InvalidArgumentsException("Too many arguments: " + args + ". Command can be run without arguments or with 1 argument");
        }
        if (args.length == 1){
            return new InputReader(args[0]);
        }
        return new InputReader();
    }

    private void executeCommand(ParsedArgs parsedArgs) throws CalculatorException {
        String commandName = parsedArgs.getCommand();
        String[] commandArgs = parsedArgs.getArgs();

        try{
            Command command = factory.createCommand(commandName);
            command.execute(context, commandArgs);
        } catch (CalculatorException e) {
            throw new CalculatorException("command '" + commandName + "': " + e.getMessage(), e);
        }
    }
}
