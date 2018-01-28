import ee.ttu.algoritmid.queue.Deque;

public class Main {
    public static void main(String[] args){

        Deque deque = new Deque();
        Deque deque2 = new Deque();
        deque.pushFirst(1);
        deque.pushFirst(2);
        deque.pushLast(6);
        deque.pushLast(7);

        deque2.pushFirst(1);
        deque2.pushFirst(2);
        deque2.pushLast(6);
        deque2.pushLast(7);

        deque2.reverse();
/*        while(!deque.isEmpty()){
            System.out.println(deque.popLast());
        }
        System.out.println("\nreversed\n");
        while(!deque2.isEmpty()){
            System.out.println(deque2.popLast());
        }*/

        System.out.println(deque.popMin());
        System.out.println(deque2.popMin());
    }
}
