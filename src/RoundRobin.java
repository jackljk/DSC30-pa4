/*
 * NAME: Jack Kai Lim
 * PID: A16919063
 */

/**
 * Implementation of the RoundRobin
 *
 * @author Jack Kai Lim
 * @since A16919063
 */
public class RoundRobin {

    /* constants */
    private static final String TASK_HANDLED = "All tasks are already handled.";

    /* instance variables */
    private DoublyLinkedList<Task> waitlist, finished;
    private int quantum, burstTime, waitTime;

    /**
     * Constructor for the RoundRobin
     * @param toHandle The task to be completed
     */
    public RoundRobin(Task[] toHandle) {
        /* Initializes all the instance variables to be used */
        if (toHandle == null || toHandle.length == 0){
            throw new IllegalArgumentException();
        }
        this.waitlist = new DoublyLinkedList<Task>();
        this.finished = new DoublyLinkedList<Task>();
        this.quantum = 4; //Default value
        this.burstTime = 0;
        this.waitTime = 0;
        for (Task task : toHandle) {
            this.waitlist.add(task);
        }
    }

    public RoundRobin(int quantum, Task[] toHandle) {
        /* Initializes all the instance variables to be used with custom quantum value */
        if (quantum < 1 || toHandle == null || toHandle.length == 0){
            throw new IllegalArgumentException();
        }
        this.waitlist = new DoublyLinkedList<Task>();
        this.finished = new DoublyLinkedList<Task>();
        this.quantum = quantum;
        this.burstTime = 0;
        this.waitTime = 0;
        for (Task task : toHandle) {
            this.waitlist.add(task);
        }
    }

    /**
     * Where everything happens.
     * Each task is process and sent back to the waitlist if it isn't completed with the quantum
     * Completed task staored in order of completion in completelist
     * Burst and wait time all stored and updated
     * @return String showing the statistics
     */
    public String handleAllTasks() {
        /* Implements round robin */
        if (this.waitlist.isEmpty()){
            /* Returns when waitlist is already empty */
            return TASK_HANDLED;
        }
        while (!this.waitlist.isEmpty()){
            /* Continues iterating when the waitlist is not empty */
            Task curr = this.waitlist.remove(0); //Gets the task at the front of the queue
            for (int i = 0;i<this.quantum;i++){
                /* Runs the number of iteration based of the quantum time provided */
                if (curr.isFinished()){
                    /*
                    Adds the curr task to the finished DLL when it is finished and breaks the
                    for loop
                    */
                    this.finished.add(curr);
                    break;
                } else {
                    /*
                    When the task is not finish adds 1 to the burst time and handles the task and
                     add the size of the waitlist as that represents 1 unit of wait time for each
                      task in the waitlist.
                     */
                    this.burstTime += 1;
                    this.waitTime += this.waitlist.size();
                    curr.handleTask();
                }
            }
            /* Extra checks to ensure the curr task is added to the finished list if finished */
            if (this.finished.contains(curr)){
                continue;
            } else if (curr.isFinished()){
                this.finished.add(curr);
            } else {
                /* If task is not finished adds it back to the end of the DLL */
                this.waitlist.add(curr);
            }
        }
        StringBuilder output = new StringBuilder("All tasks are handled within " + this.burstTime +
                " units of burst time " +
                "and " + this.waitTime + " of wait time.\n" +
                "The tasks are finished in this order:\n");
        int sizeFinished = this.finished.size();
        /* Iterates through the competed task and adds the task string to build final string. */
        for (int i = 0;i<sizeFinished;i++){
            if (i == 0){
                output.append(this.finished.remove(0).toString());
            } else {
                output.append(" -> ").append(this.finished.remove(0).toString());
            }
        }
        return output.toString();
    }

    /**
     * Main method for testing.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Task[] test1 = {new Task("A", 3), new Task("B", 4),
                        new Task("C", 4), new Task("D", 12),
                        new Task("E", 6), new Task("F", 3)};
        RoundRobin rr1 = new RoundRobin(3, test1);     // Quantum: 3, ToHandle: test1
        System.out.println(rr1.handleAllTasks());   // Burst: 32, Wait: 86, Order: AFBCED
        System.out.println();
        System.out.println(rr1.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test2 = {new Task("A", 9), new Task("B", 8),
                        new Task("C", 6), new Task("D", 4),
                        new Task("E", 4), new Task("F", 3)};
        RoundRobin rr2 = new RoundRobin(test2);  // Quantum: 4, ToHandle: test2
        System.out.println(rr2.handleAllTasks());   // Burst: 34, Wait: 123, Order: DEFBCA
        System.out.println();
        System.out.println(rr2.handleAllTasks());   // TASK_HANDLED
        System.out.println();

        Task[] test3 = {new Task("A", 7), new Task("B", 5),
                        new Task("C", 3), new Task("D", 1),
                        new Task("E", 2), new Task("F", 4),
                        new Task("G", 6), new Task("H", 8)};
        RoundRobin rr3 = new RoundRobin(3, test3);     // Quantum: 3, ToHandle: test3
        System.out.println(rr3.handleAllTasks());   // Burst: 36, Wait: 148, Order: CDEBFGAH
        System.out.println();
        System.out.println(rr3.handleAllTasks());   // TASK_HANDLED
        System.out.println();
    }
}