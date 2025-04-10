package calculator.exception;

public class DivisionByNullRuntimeException extends CalculatorRuntimeException {
    public DivisionByNullRuntimeException(double arg) {
        super("You can't divide " + arg + " by 0");
    }
}
