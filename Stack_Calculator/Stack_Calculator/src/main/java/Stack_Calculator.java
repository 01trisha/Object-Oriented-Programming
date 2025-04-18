import calculator.context.Calculator;
import calculator.context.ExecutableContex;
import calculator.exception.CalculatorIOException;
import calculator.exception.CalculatorRuntimeException;
import calculator.factory.CommandFactory;
import calculator.utils.ParserLines;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class Stack_Calculator {
    private static final Logger logger = LogManager.getLogger(Stack_Calculator.class);
    public static void main(String[] args){
        logger.info("Starting program with parameters {}", Arrays.toString(args));
        System.out.println("Program started.");

        try {
            CommandFactory factory = new CommandFactory();
            ParserLines parserLines = new ParserLines();
            ExecutableContex context = new ExecutableContex();
            Calculator calculator = new Calculator(factory, context, parserLines);
            calculator.run(args);
        } catch (CalculatorIOException e) {
            logger.fatal("Fatal Error: {}", e.getMessage());
            System.err.println("Fatal Error: " + e.getMessage());
            System.exit(0);
        }
    }
}