package calculator.exception;

// Если имя переменной невалидно (например, начинается с цифры)
public class InvalidVariableNameException extends CalculatorException {
    public InvalidVariableNameException(String message) {
        super(message);
    }
}
