package ee.ttu.algoritmid.queue;

public class Deque {

    // Don't change those lines
    Stack stack1;
    Stack stack2;
    private boolean isReversed;
    private boolean isCalledFromInnerFunction;

    public Deque() {
        this.stack1 = new Stack();
        this.stack2 = new Stack();
        isReversed = false;
        isCalledFromInnerFunction = false;
    }

    public void pushFirst(int number) {
        if (!isReversed) {
            stack2.push(number);
        } else {
            stack1.push(number);
        }
    }

    public void pushLast(int number) {
        if (!isReversed) {
            stack1.push(number);
        } else {
            stack2.push(number);
        }
    }

    public int popFirst() {
        if (isReversed && !isCalledFromInnerFunction) {
            return popFirstReversed();
        }
        isCalledFromInnerFunction = false;
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    private int popFirstReversed() {
        if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return stack1.pop();
    }

    //rollback
    public int popLast() {
        if (isReversed) {
            isCalledFromInnerFunction = true;
            return popFirst();
        } else if (stack1.isEmpty()) {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return stack1.pop();
    }

    public boolean isEmpty() {
        if (stack2.isEmpty()) {
            return stack1.isEmpty();
        } else {
            return false;
        }
    }

    public int popMin() {
        int minValue = Integer.MAX_VALUE;
        boolean firstStackHas = true;
        int firstStackInitSize = stack1.size();
        int secondStackInitSize = stack2.size();
        while (!stack1.isEmpty()) {
            int temp = stack1.pop();
            stack2.push(temp);
            if (temp < minValue) {
                minValue = temp;
            }
        }
        while (stack1.size() != firstStackInitSize) {
            stack1.push(stack2.pop());
        }
        while (!stack2.isEmpty()) {
            int temp = stack2.pop();
            stack1.push(temp);
            if (temp < minValue) {
                minValue = temp;
                firstStackHas = false;
            }
        }
        while (stack2.size() != secondStackInitSize) {
            stack2.push(stack1.pop());
        }

        if (firstStackHas) {
            return returnMinFromStackAndReturnOrder(stack1, stack2, minValue);

        } else {
            return returnMinFromStackAndReturnOrder(stack2, stack1, minValue);
        }
    }

    private int returnMinFromStackAndReturnOrder(Stack stackWithMin, Stack otherStack, int minValue) {
        int initialSize = stackWithMin.size();
        int temp;
        while ((temp = stackWithMin.pop()) != minValue) {
            otherStack.push(temp);
        }
        while (stackWithMin.size() != initialSize - 1) {
            stackWithMin.push(otherStack.pop());
        }
        return temp;
    }

    public void reverse() {
        isReversed = !isReversed;
    }

}