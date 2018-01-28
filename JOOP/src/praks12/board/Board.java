package praks12.board;

import praks12.message.Message;

import java.util.Stack;

public class Board {

    private Stack<Message> messages;

    public Board() {
        messages = new Stack<>();
    }

    public void addMessage(Message message) {
        synchronized (this) {
            messages.add(message);
            //System.out.println("Message was added");
            notifyAll();
        }
    }

    public Message getLastMessage() throws InterruptedException {
        synchronized (this) {
            while (messages.isEmpty()) {
                wait();
            }
            return messages.peek();
        }
    }

    public synchronized int getAmountOfMessages() {
        return messages.size();
    }
}
