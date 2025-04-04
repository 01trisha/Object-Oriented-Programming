import calculator.context.Calculator;
import calculator.context.ExecutableContex;
import calculator.exception.CalculatorException;
import calculator.factory.CommandFactory;
import calculator.utils.ParserLines;

public class Stack_Calculator {
    public static void main(String[] args) {
        System.out.println("Программа запущена. Ожидание ввода...");

        CommandFactory factory = new CommandFactory();
        ParserLines parserLines = new ParserLines();
        ExecutableContex context = new ExecutableContex();
        Calculator calculator = new Calculator(factory, context, parserLines);
        try {
            calculator.run(args);
        } catch (CalculatorException e) {
            System.err.println("Error: " + e.getMessage());
//            if (e.getCause() != null) {
//                System.err.println("Cause: " + e.getCause().getMessage());
//            }
        }

        System.out.println("Программа завершена.");
    }
}
