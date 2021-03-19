package tqsua.lab1stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ITqsStackTest {

    private TqsSimpleStack<String> newStack;
    private TqsSimpleStack<String> stack3elems;

    @BeforeEach
    void setUp() {
        //empty stack
        newStack = new TqsSimpleStack<>();

        //stack with some values
        stack3elems = new TqsSimpleStack<>(3);
        stack3elems.push("Aveiro");
        stack3elems.push("Braga");
        stack3elems.push("Coimbra");
    }

    @AfterEach
    void tearDown() {
    }

    //Recommended names
    //testMethod_Details
    //a)
    @Test
    void testIsEmpty_OnConstruction() {
        assertTrue(newStack.isEmpty(), "A stack must be empty on construction");
    }

    //b)
    @Test
    void testSize_OnConstruction() {
        assertEquals(newStack.size(),0, "A stack must have size 0 on construction");
    }

    //c)
    @Test
    void testPush_ToEmptyStack() {
        newStack.push("Aveiro");
        newStack.push("Braga");
        newStack.push("Coimbra");
        assertFalse(newStack.isEmpty(), "After n pushes to an empty stack, n > 0, the stack must not be empty");
        assertTrue(newStack.size()>0, "After n pushes to an empty stack, n > 0, its size must be n");
    }

    //d)
    @Test
    void testPop_AfterPush() {
        newStack.push("Aveiro");
        assertEquals(newStack.pop(), "Aveiro", "If one pushes x then pops, the value popped must be x");
    }

    //e)
    @Test
    void testPeek_AfterPush() {
        newStack.push("Aveiro");
        int oldSize = newStack.size();
        assertEquals(newStack.peek(), "Aveiro", "If one pushes x then peeks, the value returned must be x");
        assertEquals(newStack.size(), oldSize, "If one pushes x then peeks, the size must stay the same");
    }

    //f)
    @Test
    void testSize_AfterNPops() {
        for (int i=stack3elems.size(); i>0; i--) {
            stack3elems.pop();
        }
        assertEquals(stack3elems.size(), 0, "If the size is n, then after n pops, the stack must have size 0");
        assertTrue(stack3elems.isEmpty(), "If the size is n, then after n pops, the stack must be empty");
    }

    //g)
    @Test
    void testPop_FromEmpty() {
        assertThrows(NoSuchElementException.class, () -> newStack.pop() , "Popping from an empty stack must throw a NoSuchElementException");
    }

    //h)
    @Test
    void testPeek_FromEmpty() {
        assertThrows(NoSuchElementException.class, () -> newStack.peek(), "Peeking into an empty stack must throw a NoSuchElementException");
    }

    //i)
    @Test
    void testPush_FullStack() {
        assertThrows(IllegalStateException.class, () -> stack3elems.push("A"), "Pushing onto a full bounded stack must throw an IllegalStateException");
    }
}
