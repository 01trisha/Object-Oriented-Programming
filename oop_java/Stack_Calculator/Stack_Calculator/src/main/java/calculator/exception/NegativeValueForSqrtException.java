package calculator.exception;

public class NegativeValueForSqrtException extends CalculatorException{
    public NegativeValueForSqrtException(double arg) {
        super(arg + "is negative value, you cannot to take the square root of a negative number");
    }
}
