/*
 * NAME: Jack Kai Lim
 * PID: A16919063
 */

import java.util.AbstractList;
import java.util.LinkedList;

/**
 * Doubly Linked List Implementation
 * @author Jack Kai Lim
 * @since 18/04/22
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * @param element the data to be stored in the node
         */
        private Node(T element) {
            // Constructor for a singular node
            this.data = element;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            // Constructor of a node that is linked to other nodes
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         * @param element new element
         */
        public void setElement(T element) {
            // Alters the data stored in the node
            this.data = element;
        }

        /**
         * Getter Method
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            // Getter method to return the data of the node
            return this.data;
        }

        /**
         * Setter method
         * Set the next node in the list
         * @param n new next node
         */
        public void setNext(Node n) {
            // Sets the next node as another Node
            this.next = n;
        }

        /**
         * Getter method
         * Get the next node in the list
         * @return the successor node
         */
        public Node getNext() {
            // Getter method that return the next node
            return this.next;
        }

        /**
         * Setter Method
         * Set the previous node in the list
         * @param p new previous node
         */
        public void setPrev(Node p) {
            // Set the prev instance to a different node
            this.prev = p;
        }


        /**
         * Getter Method
         * Accessor to get the prev Node in the list
         * @return predecessor node
         */
        public Node getPrev() {
            // Returns the node previous to this one.
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            // Removes the nodes link to any other nodes in the list
            this.prev = null;
            this.next = null;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        // Creates an empty DLL with a dummy head and dummy tail
        Node head = new Node(null);
        Node tail = new Node(null);
        this.nelems = 0;
        this.head = head;
        this.tail = tail;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        // Implementation for throwing exceptions
        if (element == null){
            throw new NullPointerException();
        }
        // implementation of adding the new data

        if (this.nelems == 0){
            /* Add when there are no elements in the list */
            Node n = new Node(element, this.tail, this.head);
            this.head.setNext(n); //Sets the heads next link to the new node
            this.tail.setPrev(n); //Sets the tail previous link to the new node
            this.nelems += 1; // Increases the number of elements in the DLL by 1
            return true;
        } else {
            /* Add to the end of the list when there are elements in the DLL */
            Node n = new Node(element, this.tail, this.tail.getPrev());
            this.tail.prev.setNext(n); //Sets the previous last node next link o the new node
            this.tail.setPrev(n); // Sets the tails previous node to the new node
            this.nelems += 1; // Increases the number of the elements in the DLL by 1
            return true;
        }
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     * @param index The position to add the node
     * @param element The nodes element
     * @throws IndexOutOfBoundsException Throws when index is larger than the DLL
     * @throws NullPointerException Throws when either of the inputs is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // Implementation for throwing exceptions followed by
        if (index > this.nelems || index < 0){
            throw new IndexOutOfBoundsException();
        } else if (element == null){
            throw new NullPointerException();
        } else if (isEmpty()) { // implementation of adding the new data
            /* Add when there are no elements in the list */
            Node n = new Node(element, this.tail, this.head);
            this.head.setNext(n); //Sets the heads next link to the new node
            this.tail.setPrev(n); //Sets the tail previous link to the new node
            this.nelems += 1; // Increases the number of elements in the DLL by 1
        } else {
            Node current = this.head;
            for (int i = 0;i<index;i++){
                current = current.getNext();
            }
            Node n = new Node(element, current.getNext(), current);
            current.next.setPrev(n);
            current.setNext(n);
            this.nelems += 1;
        }
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        // Clear implementation, removes links of head and tail to any other node
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     * @param element The element to search for
     * @return True if the DLL contains the element, false otherwise
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        // Implementation for checkign to see if an element exist within the DLL
        Node curr = this.head;
        for (int i = 0;i<=this.nelems;i++){
            if (curr.getElement() == data){
                return true;
            }  else {
                curr = curr.getNext();
            }
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     * @param index The index of the value we are trying to get
     * @return The node at the index
     * @throws IndexOutOfBoundsException When the index is more than the size of the DLL
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        // Implementation to get the node at index
        if (index >= this.nelems || index < 0){
            throw new IndexOutOfBoundsException();
        } else {
            Node curr = this.head;
            for (int i = 0;i<index;i++){ //Goes to the node at the index
                curr = curr.getNext();
            }
            return curr.getNext().getElement();
        }
    }

    /**
     * Helper method to get the Nth node in our list
     * @param index The index to return
     * @return The Node at the index
     */
    private Node getNth(int index) {
        // Implementation to get the Nth node
        if (index > this.nelems || index < 0){
            throw new IndexOutOfBoundsException();
        } else {
            Node curr = this.head;
            for (int i = 0;i<index;i++){
                curr = curr.getNext();
            }
            return curr.getNext();
        }
    }

    /**
     * Determine if the list is empty
     * @return True if empty false otherwise
     */
    @Override
    public boolean isEmpty() {
        // Implementation to check is the DLL isEmpty
        return this.nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     * @param index The index of the node to be removed
     * @return The data of the node
     * @throws IndexOutOfBoundsException Throws if indexs is out of bounds or if the DLL is empty
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        // Remove Method
        if (index >= this.nelems || index < 0 || this.isEmpty()){
            throw new IndexOutOfBoundsException();
        } else {
            Node nToRemove = this.getNth(index); //Gets the node at the index
            nToRemove.next.setPrev(nToRemove.getPrev()); //Sets the next nodes prev to the node
            // before the current one
            nToRemove.prev.setNext(nToRemove.getNext());//Sets the prev node next to the node after
            // the current one
            nToRemove.remove();
            this.nelems -= 1; // Decreases list size by 1
            return  nToRemove.getElement();
        }
    }


    /**
     * Set the value of an element at a certain index in the list.
     * @param index The index of the node to change the element of
     * @param element The element to change to
     * @return The nodes element previously
     * @throws IndexOutOfBoundsException Throws when Index is more than the size or less than 0
     * @throws NullPointerException Throws when the element provided is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // Implementation how changing the element at an index
        if (index >= this.nelems || index < 0 || this.isEmpty()){
            // Throws if index is not in range
            throw new IndexOutOfBoundsException();
        } else if (element == null){
            // Throws if element provided is null
            throw new NullPointerException();
        } else {
            // Changing element at the index
            Node nAtIndex = this.getNth(index);
            T eAtIndex = nAtIndex.getElement();
            nAtIndex.setElement(element);
            return eAtIndex;
        }
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     * @return The number of element in the DLl
     */
    @Override
    public int size() {
        // Implementation to get the size by returning the number of elements
        return this.nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     * @return The String reprersentation of the DLL
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("[(head) -> ");
        Node curr = this.head;
        for (int i = 0;i<this.nelems;i++){
            /*
             Starts at the head and adds a string representation of each element in the DLL
             using a String builder
             */
            s.append(curr.next.getElement().toString()).append(" -> ");
            curr = curr.getNext();
        }
        s.append("(tail)]");
        return s.toString();
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * TODO: javadoc comments
     */
    public void removeMultipleOf(int base) {
        // TODO: complete implementation       
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * TODO: javadoc comments
     */
    public void swapSegment(DoublyLinkedList other, int splitIndex) {
        // TODO: complete implementation
    }

}
