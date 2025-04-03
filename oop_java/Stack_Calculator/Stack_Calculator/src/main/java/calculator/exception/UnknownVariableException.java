package calculator.exception;

public class UnknownVariableException extends CalculatorException {
    public UnknownVariableException(String variableName) {
        super("Unknown variable: " + variableName);
    }
}