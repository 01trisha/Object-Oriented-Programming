package calculator.exception;

public class NegativeValueForSqrtRuntimeException extends CalculatorRuntimeException {
    public NegativeValueForSqrtRuntimeException(double arg) {
        super(arg + " is negative value");
    }
}
