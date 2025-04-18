package calculator.exception;

public class CalculatorIOException extends Exception{
    public CalculatorIOException(String message){
        super(message);
    }

    public CalculatorIOException(String message, Throwable cause){
        super(message, cause);
    }
}

