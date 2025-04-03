package calculator.exception;

public class StackUnderflowException extends CalculatorException{
    public StackUnderflowException() {
        super("Stack is empty");
    }
}
