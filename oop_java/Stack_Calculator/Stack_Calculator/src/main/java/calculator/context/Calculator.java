package calculator.context;

import calculator.command.Command;
import calculator.exception.CalculatorIOException;
import calculator.exception.CalculatorRuntimeException;
import calculator.factory.CommandFactory;
import calculator.utils.InputReader;
import calculator.utils.ParsedArgs;
import calculator.utils.ParserLines;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Calculator {
    private static final Logger logger = LogManager.getLogger(Calculator.class);
    private final CommandFactory factory;
    private final ExecutableContex context;
    private final ParserLines parser;


    public Calculator(CommandFactory factory, ExecutableContex context, ParserLines parser) {
        this.factory = factory;
        this.context = context;
        this.parser = parser;

        logger.info("Create object of calculator");
    }

    public void run(String[] args) throws CalculatorIOException {
        logger.info("Run calculator");
        try(InputReader reader = createReader(args)) {
            logger.info("Create reader");
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    logger.debug("Read line: {}", line);
                    ParsedArgs parsedArgs = parser.parse(line);
                    if (parsedArgs == null) {
                        logger.warn("Empty line");
                        continue;
                    }
                    executeCommand(parsedArgs);
                } catch (CalculatorRuntimeException e) {
                    logger.error("Error of executing command: {}", e.getMessage());
                    System.err.println("Error of executing command: " + e.getMessage());
                }
            }
        }
    }

    InputReader createReader(String[] args) throws CalculatorIOException {
        if (args.length > 1){
            throw new CalculatorIOException("Too many arguments: " + Arrays.toString(args) + ". Command can be run without arguments or with 1 argument");
        }
        if (args.length == 1){
            logger.debug("Create reader from file: {}", args[0]);
            return new InputReader(args[0]);
        }
        logger.debug("Create reader from stdin");
        return new InputReader();
    }

    private void executeCommand(ParsedArgs parsedArgs) throws CalculatorRuntimeException {
        String commandName = parsedArgs.getCommand();
        String[] commandArgs = parsedArgs.getArgs();

        logger.debug("Execute command: {}, args: {}", commandName, Arrays.toString(commandArgs));
        try{
            Command command = factory.createCommand(commandName);
            command.execute(context, commandArgs);
        } catch (CalculatorRuntimeException e) {
            throw e;
        }
    }
}