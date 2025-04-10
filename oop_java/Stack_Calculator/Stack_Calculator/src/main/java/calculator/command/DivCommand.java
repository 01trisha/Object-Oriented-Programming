package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.DivisionByNullRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.StackUnderflowRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DivCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DivCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.info("Executing DIV command");

        if (args.length > 0){
            throw new InvalidArgumentsRuntimeException("DIV doesn't need arguments");
        }

        if (context.size() < 2){
            throw new StackUnderflowRuntimeException("DIV");
        }

        double arg2 = context.pop();
        double arg1 = context.pop();

        if (arg2 == 0.0){
            throw new DivisionByNullRuntimeException(arg1);
        }

        double result = arg1 / arg2;

        logger.info("Result of division: {}", result);

        context.push(result);
    }
}