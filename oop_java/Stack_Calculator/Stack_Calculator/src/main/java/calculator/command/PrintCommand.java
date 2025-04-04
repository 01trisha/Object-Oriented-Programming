package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.StackUnderflowException;

public class PrintCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args != null && args.length != 0){
            throw new InvalidArgumentsException("PRINT doesn't need arguments");
        }
        try {
            System.out.println(context.peek());
        }catch (StackUnderflowException e){
            throw new CalculatorException("Cannot PRINT last element" + e.getMessage() + ":");
        }
    }
}
