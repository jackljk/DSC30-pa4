import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLStackTest {
    private DLLStack<Integer> a;

    @Before
    public void init(){
        a = new DLLStack<Integer>();
    }

    @Test
    public void size() {
        a.push(10);
        assertEquals(1, a.size());
        a.push(100);
        assertEquals(2, a.size());
        for (int i = 0;i<1000;i++){
            a.push(i);
        }
        assertEquals(1002, a.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(a.isEmpty());
        a.push(10);
        assertFalse(a.isEmpty());
        a.pop();
        assertTrue(a.isEmpty());
    }

    @Test
    public void push() {
        a.push(100);
        assertEquals(new Integer(100), a.peek());
        a.push(69);
        assertEquals(new Integer(69), a.peek());
        for (int i = 0;i<1000;i++){
            a.push(i);
        }
        assertEquals(1002, a.size());
        assertEquals(new Integer(999), a.peek());
        a.pop();
        assertEquals(new Integer(998), a.peek());
    }

    @Test
    public void pop() {
        assertEquals(null, a.pop());
        for (int i = 0;i<1000;i++){
            a.push(i);
        }
        assertEquals(new Integer(999), a.pop());
        assertEquals(999, a.size());
        for (int i = 0;i<100;i++){
            a.pop();
        }
        assertEquals(new Integer(898), a.pop());
        assertEquals(898, a.size());
        assertEquals(new Integer(897), a.peek());
    }

    @Test
    public void peek() {
        assertEquals(null, a.peek());
        for (int i = 0;i<1000;i++){
            a.push(i);
        }
        assertEquals(new Integer(999), a.peek());
        for (int i = 0;i<200;i++){
            a.pop();
        }
        assertEquals(new Integer(799), a.peek());
        a.push(1000000000);
        assertEquals(new Integer(1000000000), a.peek());
    }
    @Test (expected = IllegalArgumentException.class)
    public void pushIAE(){
        a.push(null);
    }
}