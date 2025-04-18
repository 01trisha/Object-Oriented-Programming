package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SubCommand implements Command {
    private static final Logger logger = LogManager.getLogger(SubCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Executing SUB command");
        if (args.length > 0){
            throw new InvalidArgumentsRuntimeException("SUB doesn't need arguments");
        }

        if (context.size() < 2){
            throw new StackUnderflowRuntimeException("SUB");
        }

        double arg2 = context.pop();
        double arg1 = context.pop();

        double result = arg1 - arg2;

        logger.info("Result of SUB: {}", result);
        context.push(result);
    }
}