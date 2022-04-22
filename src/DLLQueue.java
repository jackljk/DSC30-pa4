/*
 * NAME: Jack Kai Lim
 * PID: A16919063
 */

/**
 * Queue Using DLL implementation
 * @param <T> generic container
 * @author Jack Kai Lim
 * @since 04/21/2022
 */
public class DLLQueue<T> {

    private DoublyLinkedList<T> queue;

    public DLLQueue() {
        /* Creates a queue where the start of the DLL is the front and the end of the DLL is the
        back of the queue */
        this.queue = new DoublyLinkedList<>();
    }

    /**
     * Gets the size of the queue currently
     * @return Size of the queue as an integer
     */
    public int size() {
        /* Uses the size method from the DLL */
        return this.queue.size();
    }

    /**
     * Checks if the Queue is empty or not
     * @return True if empty, false otherwise
     */
    public boolean isEmpty() {
        /* Uses the DLL isempty methid */
        return this.queue.isEmpty();
    }

    /**
     * Adds a new element/data to the end of the queue
     * @param data The element to be added to the queue
     */
    public void enqueue(T data) {
        /* Uses the DLL add to append to the end of the DLL which will act as the back of the
        queue */
        if (data == null){
            throw new IllegalArgumentException();
        } else {
            this.queue.add(data);
        }
    }

    /**
     * Removes the first element in the queue and returns it
     * @return The element removed from the front of the queue
     */
    public T dequeue() {
        /* Uses the remove method to always remove at index 0 which is the start of the DLL which
        *  acts as the front of the queue */
        if (this.isEmpty()){
            return null;
        } else {
            return this.queue.remove(0);
        }
    }

    /**
     * Gets the value at the front of the queue
     * @return The value at the front of the queue
     */
    public T peek() {
        /* Uses the DLL get method to get from index 0 of the queue which acts as the front of
        the queue */
        if (this.isEmpty()){
            return null;
        } else {
            return this.queue.get(0);
        }
    }

}
