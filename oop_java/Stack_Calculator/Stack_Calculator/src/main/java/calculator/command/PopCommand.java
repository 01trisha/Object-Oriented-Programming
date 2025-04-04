package calculator.command;

import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.exception.InvalidArgumentsException;
import calculator.exception.StackUnderflowException;

public class PopCommand implements Command {
    @Override
    public void execute(ExecutableContex context, String[] args) throws CalculatorException {
        if (args != null && args.length != 0){
            throw new InvalidArgumentsException("POP doesn't need arguments");
        }

        try {
            context.pop();
        }catch (StackUnderflowException e){
            throw new CalculatorException("Need 1 or more elements in stack: " + e.getMessage());
        }
    }
}
