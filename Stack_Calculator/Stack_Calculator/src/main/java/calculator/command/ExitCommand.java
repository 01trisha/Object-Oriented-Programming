package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExitCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ExitCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Executing EXIT command");

        if (args.length > 0) {
            throw new InvalidArgumentsRuntimeException("EXIT doesn't need arguments");
        }

        logger.info("Terminating program by EXIT command");
        System.exit(0);
    }
}