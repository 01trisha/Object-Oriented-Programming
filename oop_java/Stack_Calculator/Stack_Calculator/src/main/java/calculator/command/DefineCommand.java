package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;
import calculator.exception.InvalidArgumentsRuntimeException;
import calculator.exception.InvalidVariableNameRuntimeException;
import calculator.exception.NotANumberRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class DefineCommand implements Command {
    private static final Logger logger = LogManager.getLogger(DefineCommand.class);

    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException {
        logger.debug("Execute command: {}, args: {}", getClass().getSimpleName(), Arrays.toString(args));
        if (args.length != 2){
            throw new InvalidArgumentsRuntimeException("DEFINE need 2 arguments");
        }

        String nameVar = args[0];
        String val = args[1];

        logger.debug("Check if variable name '{}' is a number", nameVar);
        if (isNumber(nameVar)){
            throw new InvalidVariableNameRuntimeException("Variable name cannot be a number: " + nameVar);
        }

        logger.debug("Check if variable value '{}' is a number", val);
        if (!isNumber(val)){
            throw new NotANumberRuntimeException("Not a valid number: " + val);
        }

        double value = Double.parseDouble(val);
        context.put(nameVar, value);
        logger.info("Variable '{}' with value '{}' added", nameVar, value);

    }

    private boolean isNumber(String str) {
        try{
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }
}