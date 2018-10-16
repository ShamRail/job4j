package bomberman;

public class Cell {

    private final int x;
    private final int y;

    /**
     * Конструктор по умолчанию со случайными координатами в диапозоне [0;19].
     * */

    public Cell(int boardSize) {
        this.x = (int) (Math.random() * boardSize);
        this.y = (int) (Math.random() * boardSize);
    }

    public Cell(int x, int y) {
        this.x =  x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

}
