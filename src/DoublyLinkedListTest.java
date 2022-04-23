import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {
    private DoublyLinkedList<Integer> a;
    private DoublyLinkedList<String> b;
    private DoublyLinkedList<int[]> c;

    @Before
    public void init(){
        a = new DoublyLinkedList<Integer>();
        b = new DoublyLinkedList<String>();
        c = new DoublyLinkedList<int[]>();
    }

    @Test
    public void add() {
        /* Using integers */
        assertTrue(a.add(new Integer(1)));
        assertEquals(new Integer(1), a.get(0));
        for (int i = 0;i<100;i++){
            a.add(i);
        }
        assertEquals(101, a.size());
        assertEquals(new Integer(99), a.get(100));
        /* Using Strings */
        assertTrue(b.add("Hello"));
        assertEquals("Hello", b.get(0));
        b.add("TEST");
        b.add("TEST2");
        b.add(("TEST3"));
        assertEquals("TEST2", b.get(2));
        assertEquals("TEST", b.get(1));
        /* using Array(Objects) */
        int[] array = new int[]{1, 2, 3};
        c.add(array);
        assertEquals(array, c.get(0));
        for (int i = 0;i<100;i++){
            c.add(array);
        }
        int[] array2 = new int[]{3, 4, 5};
        c.add(array2);
        assertEquals(array, c.get(50));
        assertEquals(array2, c.get(101));
    }

    @Test
    public void testAdd() {
        /* On Integers */
        for (int i = 0;i<20;i++){
            a.add(i);
        }
        a.add(10, 111);
        assertEquals(new Integer(111), a.get(10));
        a.add(0, 2022);
        assertEquals(new Integer(2022), a.get(0));
        /* On strings */
        for (int i = 0;i<20;i++){
            b.add("Index: " + i);
        }
        b.add(4 ,"Oops everything moves by 1");
        assertEquals("Oops everything moves by 1", b.get(4));
        /* On Arrays(Objects) */
        int[] array = new int[]{1, 2, 3};
        for (int i = 0;i<20;i++){
            c.add(array);
        }
        assertEquals(array, c.get(10));
        int[] array2 = new int[]{100, 200, 300};
        c.add(18, array2);
        assertEquals(array2, c.get(18));
    }

    @Test
    public void clear() {
        /* With integers */
        for (int i = 0;i<100;i++){
            a.add(10);
        }
        assertEquals(100, a.size());
        a.clear();
        assertEquals(0, a.size());
        assertEquals("[(head) -> (tail)]", a.toString());
        a.clear(); //Test to see if even when empty the function runs and does nothing
        /* With Strings */
        for (int i = 0;i<10;i++){
            b.add("TEST");
        }
        assertEquals(10, b.size());
        assertEquals("[(head) -> TEST -> TEST -> TEST -> TEST -> TEST -> TEST" +
                " -> TEST -> TEST -> TEST -> TEST -> (tail)]", b.toString());
        b.clear();
        assertEquals(0, b.size());
        assertEquals("[(head) -> (tail)]", b.toString());
        /* Array Objects */
        for (int i = 0;i<5;i++){
            c.add(new int[]{1, 2, 3});
        }
        assertEquals(5, c.size());
        c.clear();
        assertEquals(0, c.size());
        //Can't test to String as the memory address will not be the same depending on how you
        // run the test.
    }

    @Test
    public void contains() {
        /* Testing with Integers */
        for (int i = 0;i<100;i++){
            a.add(i);
        }
        assertTrue(a.contains(69));
        assertTrue(a.contains(99));
        assertFalse(a.contains(100));
        /* Testing with Strings */
        b.add("Hola 1");
        b.add("Hola 10");
        b.add("Hola 100");
        b.add("Hola 1000");
        b.add("Hola 10000");
        b.add("Hola 100000");
        b.add("Hola 1000000");
        b.add("Hola 10000000");
        String test = "Hola 1000";
        assertTrue(b.contains(test));
        assertTrue(b.contains("Hola 1"));
        assertFalse(b.contains("Hola"));
    }

    @Test
    public void get() {
        /* Testing with Integers */
        for (int i = 0;i<100;i++){
            a.add(i*i);
        }
        assertEquals(new Integer(64), a.get(8));
        assertEquals(new Integer(99*99), a.get(99));
        /* Testing with String */
        for (int i = 0;i<10;i++){
            b.add("Hola " + i);
        }
        assertEquals("Hola 9", b.get(9));
        assertEquals("Hola 2", b.get(2));
    }

    @Test
    public void isEmpty() {
        /* With Integers */
        assertTrue(a.isEmpty());
        a.add(10);
        assertFalse(a.isEmpty());
        /* Testing String */
        assertTrue(b.isEmpty());
        b.add("Hello");
        assertFalse(a.isEmpty());
        /* Testing with Array */
        assertTrue(c.isEmpty());
        c.add(new int[]{1, 2, 3});
        assertFalse(c.isEmpty());
    }

    @Test
    public void remove() {
        /* Testing with Integer */
        for (int i = 0;i<100;i++){
            a.add(i);
        }
        assertEquals(100, a.size());
        assertEquals(new Integer(40), a.remove(40));
        assertEquals(99, a.size());
        assertEquals(new Integer(0), a.remove(0));
        assertEquals(98, a.size());
        for (int i = 0;i<20;i++){
            a.remove(i);
        }
        assertEquals(78, a.size());
        a.add(0, 2022);
        assertEquals(new Integer(2022), a.remove(0));
        assertEquals(78, a.size());
    }

    @Test
    public void set() {
        /* With Integers */
        for (int i = 0;i<100;i++){
            a.add(i);
        }
        a.set(10, 222);
        assertEquals(new Integer(222), a.get(10));
        assertEquals(100, a.size());
        a.set(0, 6969);
        assertEquals(new Integer(6969), a.get(0));
        assertEquals(100, a.size());
        a.set(99, 2000000);
        assertEquals(new Integer(2000000), a.get(99));
        assertEquals(100, a.size());
        /* With Strings */
        b.add("Bonjour");
        b.set(0, "Hello");
        assertEquals("Hello", b.get(0));
        assertEquals(1, b.size());
    }

    @Test
    public void size() {
        assertEquals(0, a.size());
        for (int i = 0;i<10000;i++){
            a.add(1);
        }
        assertEquals(10000, a.size());
        for (int i = 0;i<100;i++){
            a.remove(3);
        }
        assertEquals(10000 - 100, a.size());
    }

    @Test
    public void testToString() {
        /* Integer */
        for (int i = 0;i<10;i++){
            a.add(i*i);
        }
        assertEquals("[(head) -> 0 -> 1 -> 4 -> 9 -> 16 -> 25" +
                " -> 36 -> 49 -> 64 -> 81 -> (tail)]", a.toString());
        /* Strings */
        for (int i = 0;i<10;i++){
            b.add("Hello " + i);
        }
        assertEquals("[(head) -> Hello 0 -> Hello 1 -> Hello 2 -> Hello 3 -> Hello 4 " +
                "-> Hello 5 -> Hello 6 -> Hello 7 -> Hello 8 -> Hello 9 -> (tail)]", b.toString());
        /* Empty DLL test */
        assertEquals("[(head) -> (tail)]", c.toString());
    }

    @Test (expected = NullPointerException.class)
    public void addNPE1(){
        a.add(null);
    }
    @Test (expected = NullPointerException.class)
    public void addNPE2(){
        a.add(0, null);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void addIOOBE(){
        a.add(2, 10);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void getIOOBE1(){
        a.get(0);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void getIOOBE2(){
        a.add(1);
        a.get(1);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void removeIOOBE1(){
        a.add(1);
        a.remove(1);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void removeIOOBE2(){
        a.remove(0);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void setIOOBE1(){
        a.set(0, 100);
    }
    @Test (expected = IndexOutOfBoundsException.class)
    public void setIOOBE2(){
        a.add(100);
        a.set(1, 100);
    }
    @Test (expected = NullPointerException.class)
    public void setNPE(){
        a.add(1);
        a.set(0, null);
    }

    @Test
    public void removeMultipleOfTest(){
        for (int i = 0;i<100;i++){
            a.add(i);
        }
        a.removeMultipleOf(3);
        assertEquals(66, a.size());
        a.clear();
        for (int i = 0;i<10;i++){
            b.add("Test" + i);
        }
        b.removeMultipleOf(4);
        assertEquals("[(head) -> Test1 -> Test2 -> Test3 -> " +
                "Test5 -> Test6 -> Test7 -> Test9 -> (tail)]", b.toString());
        b.removeMultipleOf(2);
        assertEquals("[(head) -> Test2 -> " +
                "Test5 -> Test7 -> (tail)]", b.toString());
    }

    @Test
    public void swapTest(){
        DoublyLinkedList<Integer> d = new DoublyLinkedList<Integer>();
        for (int i = 0;i<10;i++){
            a.add(i);
        }
        for (int i = 9;i>=0;i--){
            d.add(i);
        }
        a.swapSegment(d, 3);
        assertEquals("[(head) -> 9 -> 8 -> 7 -> 6 -> 4 ->" +
                " 5 -> 6 -> 7 -> 8 -> 9 -> (tail)]", a.toString());
        assertEquals("[(head) -> 0 -> 1 -> 2 -> 3 -> 5 -> 4 " +
                "-> 3 -> 2 -> 1 -> 0 -> (tail)]", d.toString());
        a.swapSegment(d, 0);
        assertEquals("[(head) -> 0 -> 8 -> 7 -> 6 -> 4 ->" +
                " 5 -> 6 -> 7 -> 8 -> 9 -> (tail)]", a.toString());}
}
