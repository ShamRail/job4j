package bomberman;

public class Monster extends Character {

    public Monster(Board board) {
        super(board);
    }
    /**
     * run.
     * Двигает монстра пока поток не будет остановлен.
     * Пытается сдвинуть монстра, генерируя случайное направление, пока не получится это сделать.
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
            if (((previousX + deltaX) >= 0 && (previousX + deltaX) <= 19)
                    && ((previousY + deltaY) >= 0 && (previousY + deltaY) <= 19)) {
                result = new Cell(previousX + deltaX, previousY + deltaY);
            }
        } while (previousX == result.getX() && previousY == result.getY());
        return result;
    }

}
