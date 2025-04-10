package calculator.exception;

// Если имя переменной невалидно (например, начинается с цифры)
public class InvalidVariableNameRuntimeException extends CalculatorRuntimeException {
    public InvalidVariableNameRuntimeException(String message) {
        super(message);
    }
}
