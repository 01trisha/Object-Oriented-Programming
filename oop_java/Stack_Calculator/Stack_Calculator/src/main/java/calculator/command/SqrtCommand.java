package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.NegativeValueForSqrtException;
import calculator.exception.StackUnderflowException;


public class SqrtCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args.length > 0){
            throw new InvalidArgumentsException("SQRT doesn't need arguments");
        }

        try{
            double arg = context.pop();

            if (arg < 0){
                throw new NegativeValueForSqrtException(arg);
            }

            double result = Math.sqrt(arg);
            context.push(result);
        }catch (StackUnderflowException e){
            throw new CalculatorException("Need 2 elements for SQRT", e);
        }
    }
}
