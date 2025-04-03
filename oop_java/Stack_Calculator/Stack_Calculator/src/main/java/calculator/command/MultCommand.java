package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.StackUnderflowException;

public class MultCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args.length > 0){
            throw new InvalidArgumentsException("* doesn't need arguments");
        }

        try{
            double arg1 = context.pop();
            double arg2 = context.pop();

            double result = arg1 * arg2;

            context.push(result);
        }catch (StackUnderflowException e){
            throw new CalculatorException("Need 2 elements in stack for *", e);
        }
    }
}
