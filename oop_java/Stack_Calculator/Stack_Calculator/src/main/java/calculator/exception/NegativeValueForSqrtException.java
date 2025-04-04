package calculator.exception;

public class NegativeValueForSqrtException extends CalculatorException{
    public NegativeValueForSqrtException(double arg) {
        super(arg + " is negative value");
    }
}
