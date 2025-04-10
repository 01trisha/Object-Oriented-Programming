package calculator.command;


import calculator.context.ExecutableContex;
import calculator.exception.CalculatorRuntimeException;

public interface Command {
    void execute(ExecutableContex context, String[] args) throws CalculatorRuntimeException;
}