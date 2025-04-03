package calculator.exception;

public class DivisionByNullException extends CalculatorException{
    public DivisionByNullException(double arg) {
        super("You can't divide " + arg + " by 0");
    }
}
