package bomberman;

public class Bomberman extends Character {

    public Bomberman(Board board) {
        super(board);
    }

    private final Cell up = new Cell(0, 1);
    private final Cell down = new Cell(0, -1);
    private final Cell left = new Cell(-1, 0);
    private final Cell right = new Cell(1, 1);

    /**
     * run.
     * Двигает героя, пока поток не будет остановлен.
     * Пытается сдинуть героя на верх, вниз, влево, вправо.
     * Затем новые координаты перезаписываются в текущуюю позицию.
     * */

    @Override
    public void run() {
        try {
            initStartPosition();
            while (!Thread.currentThread().isInterrupted()) {
                int directionID = (int) (Math.random() * 4);
                switch (directionID) {
                    case 0 :
                        if (moveToDirection(up)) {
                            System.out.print(String.format("%s has went Up. ", Thread.currentThread().getName()));
                            break;
                        }
                    case 1 :
                        if (moveToDirection(down)) {
                            System.out.print(String.format("%s has went Down. ", Thread.currentThread().getName()));
                            break;
                        }
                    case 2 :
                        if (moveToDirection(left)) {
                            System.out.print(String.format("%s has went Left. ", Thread.currentThread().getName()));
                            break;
                        }
                    case 3 :
                        if (moveToDirection(right)) {
                            System.out.print(String.format("%s has went Right. ", Thread.currentThread().getName()));
                            break;
                        }
                     default:
                         System.out.print("No move. ");
                }
                System.out.println(String.format("Current position (%s, %s)", currentPosition.getX(), currentPosition.getY()));
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

    private boolean moveToDirection(Cell direction) throws InterruptedException {
        boolean result = false;
        if ((currentPosition.getX() + direction.getX() >= 0 && currentPosition.getX() + direction.getX() < board.getSize())
                && (currentPosition.getY() + direction.getY() >= 0 && currentPosition.getY() + direction.getY() < board.getSize())) {
            Cell appointPosition = new Cell(currentPosition.getX() + direction.getX(), currentPosition.getY() + direction.getY());
            result = board.move(currentPosition, appointPosition);
            if (result) {
                currentPosition = appointPosition;
            }
        }
        return result;
    }
}
