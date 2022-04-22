/*
 * NAME: Jack Kai Lim
 * PID: A16919063
 */

/**
 * Stack class using DLL
 * @param <T> generic container
 * @author Jack Kai Lim
 * @since 04/21/2022
 */
public class DLLStack<T> {

    private DoublyLinkedList<T> stack;

    /**
     * Initializes the stack, where the start of the DLL is the top of the stack.
     */
    public DLLStack() {
        /* Creates a DLL */
        this.stack = new DoublyLinkedList<>();
    }

    /**
     * Gets the size of the DLL which will represent the stack
     * @return Size of Stack
     */
    public int size() {
        /* Using the DLL size method to get the size of the stack */
        return this.stack.size();
    }

    /**
     * Checks if the Stack is empty or not
     * @return True is it is empty, false otherwise
     */
    public boolean isEmpty() {
        /* Uses the DLL is empty method to  check if the stack is empty or not */
        return this.stack.isEmpty();
    }

    /**
     * Adds a new element/item to the stack
     * @param data The element to add to the stack
     */
    public void push(T data) {
        /* Uses the DLL add method to add to the top of the stack which is the end of the DLL */
        if (data == null){
            throw new IllegalArgumentException();
        } else {
            this.stack.add(0, data);
        }
    }

    /**
     * Removes and returns the element at the top of the stack
     * @return The popped element
     */
    public T pop() {
        /* If the stack size is 0, returns null else removes the element at the top of the stack
        and returns it using the DLL method remove */
        T popData;
        if (this.size() == 0) {
            return null;
        } else {
            popData = this.stack.remove(0);
        }
        return popData;
    }

    /**
     * Checks to see what the element is at the top of the stack
     * @return
     */
    public T peek() {
        /* If the stack size is 0, returns null else returns the value at the top of the stack */
        if (this.size() == 0){
            return null;
        } else{
            return this.stack.get(0);
        }
    }

}
