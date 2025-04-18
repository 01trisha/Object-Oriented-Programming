package calculator.exception;

public class CalculatorRuntimeException extends RuntimeException {
    public CalculatorRuntimeException(String message){
        super(message);
    }

    public CalculatorRuntimeException(String message, Throwable cause){
        super(message, cause);
    }
}
