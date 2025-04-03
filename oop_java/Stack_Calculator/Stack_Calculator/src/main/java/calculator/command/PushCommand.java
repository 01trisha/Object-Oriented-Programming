package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.UnknownVariableException;

public class PushCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args.length != 1){
            throw new InvalidArgumentsException("PUSH needs 1 arguments");
        }

        String arg = args[0];

        if (!isNumber(arg)){
            try {
                double val = context.get(arg);
            }catch (UnknownVariableException e){
                throw new CalculatorException("Unknown variable name: " + arg, e);
            }
        }else{
            double val = Double.parseDouble(arg);
            context.push(val);
        }
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
