package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.UnknownVariableRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class PushCommand implements Command {
    private static final Logger logger = LogManager.getLogger(PushCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Execute command: {}, args: {}", getClass().getSimpleName(), Arrays.toString(args));

        if (args.length != 1){
            throw new InvalidArgumentsRuntimeException("PUSH needs 1 arguments");
        }

        String arg = args[0];

        if (!isNumber(arg)){
            logger.debug("Try to get variable: {}", arg);
            try {
                double val = context.get(arg);
                logger.debug("Variable found, pushing value: {}", val);
                context.push(val);
            }catch (UnknownVariableRuntimeException e){
                throw new CalculatorRuntimeException("Unknown variable name: " + arg, e);
            }
        }else{
            double val = Double.parseDouble(arg);
            logger.debug("Number parsed, pushing value: {}", val);
            context.push(val);
        }
    }

    private boolean isNumber(String str) {
        try{
            logger.debug("Try to parse number: {}", str);
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e) {
            logger.debug("Not a number: {}", str);
            return false;
        }
    }
}