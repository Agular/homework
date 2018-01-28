package ee.ttu.algoritmid.queue;

public class Queue {

    // Don't change those lines
    final Stack stack1;
    final Stack stack2;

    public Queue() {
        this.stack1 = new Stack();
        this.stack2 = new Stack();
    }

    // Implement adding an element to the queue.
    public void enqueue(int number) {
        stack1.push(number);
    }

    // Implement removing an element from the queue.
    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public boolean isEmpty() {
        if (stack2.isEmpty()) {
            return stack1.isEmpty();
        } else {
            return false;
        }
    }
}