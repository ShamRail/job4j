package bomberman;

public class Bomberman implements Runnable {

    private Cell currentPosition;

    private final Board board;

    public Bomberman(Board board) {
        this.board = board;
    }

    /**
     * run.
     * Двигает героя, пока поток не будет остановлен.
     * Генерирует случайную начальную позицию, потом пытается сдинуть в случайном направлении.
     * Затем новые координаты перезаписываются в текущуюю позицию.
     * */

    @Override
    public void run() {
        try {
            initStartPosition();
            while (!Thread.currentThread().isInterrupted()) {
                boolean isMoved;
                Cell appointPosition;
                do {
                    appointPosition = getRandomAppointPosition();
                    isMoved = board.move(currentPosition, appointPosition);
                } while (!isMoved);
                currentPosition = appointPosition;
                System.out.println(String.format("%s is in (%s, %s)", Thread.currentThread().getName(), currentPosition.getX(), currentPosition.getY()));
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * getRandomAppointPosition.
     * Генерирует случайную позицию назначения.
     * Возможный сдвиг по координатам -1, 0, 1.
     * @return новая позиция.
     * */

    private Cell getRandomAppointPosition() {
        Cell result = new Cell(currentPosition.getX(), currentPosition.getY());
        int deltaX;
        int deltaY;
        int previousX;
        int previousY;
        do {
            deltaX = -1 + (int) (Math.random() * 3);
            deltaY = -1 + (int) (Math.random() * 3);
            previousX = currentPosition.getX();
            previousY = currentPosition.getY();
            if (((previousX + deltaX) >= 0 && (previousX + deltaX) <= 9)
                && ((previousY + deltaY) >= 0 && (previousY + deltaY) <= 9)) {
                result = new Cell(previousX + deltaX, previousY + deltaY);
            }
        } while (previousX == result.getX() && previousY == result.getY());
        return result;
    }

    /**
     * initStartPosition.
     * Генерирует начальную позицию, до тех пор пока она не создастся и соответствующая позиция не заблокируется.
     * */

    private void initStartPosition() throws InterruptedException {
        boolean isCreated;
        Cell startPosition;
        do {
            startPosition = new Cell();
            isCreated = board.lockStartPosition(startPosition);
        } while (!isCreated);
        currentPosition = new Cell(startPosition.getX(), startPosition.getY());
        System.out.println(String.format("%s is created in (%s, %s)", Thread.currentThread().getName(), currentPosition.getX(), currentPosition.getY()));
    }

}
