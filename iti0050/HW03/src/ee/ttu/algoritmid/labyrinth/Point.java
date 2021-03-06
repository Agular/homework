package ee.ttu.algoritmid.labyrinth;

public class Point {

    private int row;
    private int col;
    private int value;

    public Point(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }
}
