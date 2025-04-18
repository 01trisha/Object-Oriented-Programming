package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrintCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PrintCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Executing command: PRINT");
        if (args != null && args.length != 0){
            throw new InvalidArgumentsRuntimeException("PRINT doesn't need arguments");
        }

        if (context.size() < 1) {
            throw new StackUnderflowRuntimeException("PRINT");
        }

        double value = context.peek();
        logger.info("Print value from stack: {}", value);
        System.out.println(value);
    }
}