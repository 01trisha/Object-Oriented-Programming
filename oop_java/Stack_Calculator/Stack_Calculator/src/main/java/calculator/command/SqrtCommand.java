package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.NegativeValueForSqrtRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SqrtCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SqrtCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Executing SQRT command");

        if (args.length > 0){
            throw new InvalidArgumentsRuntimeException("SQRT doesn't need arguments");
        }

        if (context.size() < 1){
            throw new StackUnderflowRuntimeException("SQRT");
        }

        double arg = context.pop();

        if (arg < 0){
            throw new NegativeValueForSqrtRuntimeException(arg);
        }

        double result = Math.sqrt(arg);
        logger.debug("Pushing result to stack: {}", result);
        context.push(result);
    }
}