package calculator.context;


import calculator.exception.StackUnderflowException;
import calculator.exception.UnknownVariableException;

import java.util.*;

public class ExecutableContex{
    private final LinkedList<Double> stack = new LinkedList<>();
    private final Map<String, Double> variable = new HashMap<>();

    public void push(double value){
        stack.push(value);
    }

    public double pop() throws StackUnderflowException{
        if (stack.isEmpty()){
            throw new StackUnderflowException();
        }
        return stack.pop();
    }

    public double peek() throws StackUnderflowException {
        if (stack.isEmpty()){
            throw new StackUnderflowException();
        }
        return stack.peek();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void put(String name, double value){
        variable.put(name, value);
    }

    public double get(String name) throws UnknownVariableException {
        if (!variable.containsKey(name)){
            throw new UnknownVariableException(name);
        }
        return variable.get(name);
    }

}
