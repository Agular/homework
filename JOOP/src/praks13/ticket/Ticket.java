package praks13.ticket;

public class Ticket {

    private int id;
    private int weight;

    public Ticket(int id, int weight) {
        this.id = id;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }
}
