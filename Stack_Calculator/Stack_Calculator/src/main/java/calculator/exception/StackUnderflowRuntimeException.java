package calculator.exception;

public class StackUnderflowRuntimeException extends CalculatorRuntimeException {
    public StackUnderflowRuntimeException(String nameCommand) {
        super("Stack has not enough arguments for " + nameCommand);
    }
}
