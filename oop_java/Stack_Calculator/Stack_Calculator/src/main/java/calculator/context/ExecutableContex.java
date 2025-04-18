package calculator.context;

import calculator.exception.StackUnderflowRuntimeException;
import calculator.exception.UnknownVariableRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ExecutableContex{
    private static final Logger logger = LogManager.getLogger(ExecutableContex.class);
    private final LinkedList<Double> stack = new LinkedList<>();
    private final Map<String, Double> variable = new HashMap<>();

    public ExecutableContex() {
        logger.info("Create object of Context");
    }

    public void push(double value) {
        logger.info("Pushing value to stack: {}", value);
        stack.push(value);
    }

    public double pop() {
        if (stack.isEmpty()) {
            logger.error("Stack underflow on pop");
            throw new StackUnderflowRuntimeException("pop");
        }
        double value = stack.pop();
        logger.info("Popped value from stack: {}", value);
        return value;
    }

    public double peek() throws StackUnderflowRuntimeException {
        if (stack.isEmpty()) {
            logger.error("Stack underflow on peek");
            throw new StackUnderflowRuntimeException("peek");
        }
        double value = stack.peek();
        logger.info("Peeked value from stack: {}", value);
        return value;
    }

    public int size() {
        return stack.size();
    }

    public void put(String name, double value) {
        logger.info("Storing variable '{}' with value: {}", name, value);
        variable.put(name, value);
    }

    public double get(String name) throws UnknownVariableRuntimeException {
        if (!variable.containsKey(name)) {
            logger.error("Unknown variable name: {}", name);
            throw new UnknownVariableRuntimeException(name);
        }
        double value = variable.get(name);
        logger.info("Retrieved variable '{}' with value: {}", name, value);
        return value;
    }
}