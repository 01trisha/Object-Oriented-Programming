package calculator.exception;

public class UnknownVariableRuntimeException extends CalculatorRuntimeException {
    public UnknownVariableRuntimeException(String variableName) {
        super("Unknown variable: " + variableName);
    }
}