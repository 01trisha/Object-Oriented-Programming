package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        if (args.length > 0) {
            throw new InvalidArgumentsRuntimeException("AddCommand doesn't need arguments");
        }
        if (context.size() < 2) {
            throw new StackUnderflowRuntimeException("ADD");
        }
        double arg1 = context.pop();
        double arg2 = context.pop();

        double result = arg1 + arg2;

        logger.info("Add {} and {}, result: {}", arg1, arg2, result);
        context.push(result);
    }
}