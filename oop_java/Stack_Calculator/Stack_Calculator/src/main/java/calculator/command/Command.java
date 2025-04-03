package calculator.command;


import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;

public interface Command {
    void execute(ExecutableContex context, String[] args) throws CalculatorException;
}