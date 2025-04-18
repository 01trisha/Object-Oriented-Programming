package calculator.context;

import calculator.exception.StackUnderflowRuntimeException;
import calculator.exception.UnknownVariableRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutableContexTest{

    private ExecutableContex context;

    @BeforeEach
    void setUp(){
        context = new ExecutableContex();
    }

    @Test
    void testPushAndPop(){
        context.push(3.14);
        assertEquals(3.14, context.pop(), 0.00001);
    }

    @Test
    void testPeek(){
        context.push(2.71);
        assertEquals(2.71, context.peek(), 0.00001);
        assertEquals(1, context.size());
    }

    @Test
    void testPopEmptyStackThrows(){
        assertThrows(StackUnderflowRuntimeException.class, () -> context.pop());
    }

    @Test
    void testPeekEmptyStackThrows(){
        assertThrows(StackUnderflowRuntimeException.class, () -> context.peek());
    }

    @Test
    void testPutAndGetVariable(){
        context.put("x", 42.0);
        assertEquals(42.0, context.get("x"), 0.00001);
    }

    @Test
    void testGetUnknownVariableThrows(){
        assertThrows(UnknownVariableRuntimeException.class, () -> context.get("y"));
    }

    @Test
    void testStackSize(){
        assertEquals(0, context.size());
        context.push(1.0);
        context.push(2.0);
        assertEquals(2, context.size());
        context.pop();
        assertEquals(1, context.size());
    }
}
