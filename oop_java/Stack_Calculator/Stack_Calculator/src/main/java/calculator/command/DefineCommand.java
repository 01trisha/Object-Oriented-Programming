package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.InvalidVariableNameException;
import calculator.exception.NotANumberException;

public class DefineCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args.length != 2){
            throw new InvalidArgumentsException("DEFINE need 2 arguments");
        }

        String nameVar = args[0];
        String val = args[1];

        if (isNumber(nameVar)){
            throw new InvalidVariableNameException("Variable name cannot be a number: " + nameVar);
        }

        if (!isNumber(val)){
            throw new NotANumberException("Not a valid number: " + val);
        }

        double value = Double.parseDouble(val);
        context.put(nameVar, value);

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
