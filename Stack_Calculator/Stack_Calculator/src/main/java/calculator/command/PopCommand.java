package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PopCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Executing POP command");

        if (args != null && args.length != 0){
            throw new InvalidArgumentsRuntimeException("POP doesn't need arguments");
        }

        if (context.size() < 1) {
            throw new StackUnderflowRuntimeException("POP");
        }

        context.pop();
        logger.debug("Poped value from stack");
    }
}