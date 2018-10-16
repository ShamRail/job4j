package bomberman;

public abstract class Character implements Runnable {

    protected Cell currentPosition;

    protected final Board board;

    public Character(Board board) {
        this.board = board;
        this.initStartPosition();
    }

    /**
     * initStartPosition.
     * Генерирует начальную позицию, до тех пор пока она не создастся и соответствующая позиция не заблокируется.
     * */

    protected void initStartPosition() {
        try {
            boolean isCreated;
            Cell startPosition;
            do {
                startPosition = new Cell(board.getSize());
                isCreated = board.lockStartPosition(startPosition);
            } while (!isCreated);
            currentPosition = new Cell(startPosition.getX(), startPosition.getY());
            System.out.println(String.format("%s is created in (%s, %s)", Thread.currentThread().getName(), currentPosition.getX(), currentPosition.getY()));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
