import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DLLQueueTest {
    private DLLQueue<Integer> a;

    @Before
    public void init(){
        a = new DLLQueue<Integer>();
    }


    @Test
    public void size() {
        assertEquals(0, a.size());
        a.enqueue(100);
        assertEquals(1, a.size());
        for (int i = 0;i<100;i++){
            a.enqueue(i);
        }
        assertEquals(101, a.size());
        a.dequeue();
        assertEquals(100, a.size());
    }

    @Test
    public void isEmpty() {
        assertTrue(a.isEmpty());
        a.enqueue(10);
        assertFalse(a.isEmpty());
        a.dequeue();
        assertTrue(a.isEmpty());
        for (int i = 0;i<100;i++){
            a.enqueue(i);
        }
        assertFalse(a.isEmpty());
    }

    @Test
    public void enqueue() {
        for (int i = 0;i<100;i++){
            a.enqueue(i);
        }
        assertEquals(new Integer(0), a.peek());
        a.dequeue();
        assertEquals(new Integer(1), a.peek());
        for (int i = 0;i<10;i++){
            a.dequeue();
        }
        assertEquals(new Integer(11), a.peek());
    }

    @Test
    public void dequeue() {
        assertEquals(null, a.dequeue());
        for (int i = 0;i<100;i++){
            a.enqueue(i);
            a.dequeue();
        }
        assertEquals(0, a.size());
        assertTrue(a.isEmpty());
        for (int i = 0;i<100;i++){
            a.enqueue(i);
        }
        for (int i = 0;i<10;i++){
            a.dequeue();
        }
        assertEquals(new Integer(10), a.peek());
        assertEquals(new Integer(10), a.dequeue());
        for (int i = 0;i<10;i++){
            assertEquals(new Integer(11 + i), a.dequeue());
        }
    }

    @Test
    public void peek() {
        assertEquals(null, a.peek());
        for (int i = 0;i<100;i++){
            a.enqueue(i);
        }
        assertEquals(new Integer(0), a.peek());
        for (int i = 0;i<10;i++){
            a.dequeue();
        }
        assertEquals(new Integer(10), a.peek());
        a.enqueue(1000);
        assertEquals(new Integer(10), a.peek());
        a.dequeue();
        assertEquals(new Integer(11), a.peek());
    }
    @Test (expected = IllegalArgumentException.class)
    public void enqueueIAE(){
        a.enqueue(null);
    }
}
