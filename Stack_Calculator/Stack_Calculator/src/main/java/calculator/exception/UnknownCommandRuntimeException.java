package calculator.exception;

public class UnknownCommandRuntimeException extends CalculatorRuntimeException{
    public UnknownCommandRuntimeException(String message){
        super(message);
    }
    public UnknownCommandRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
