package bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {

    private final static int SIZE = 10;

    private final ReentrantLock[][] board = new ReentrantLock[SIZE][SIZE];

    public Board() {
        this.fillBoard();
    }

    /**
     * fillBoard.
     * Иницилизирует доску объектами ReentrantLock.
     * */

    private void fillBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                board[row][column] = new ReentrantLock();
            }
        }
    }

    /**
     * move.
     * Двигает Bomberman'a.
     * Пытается получить доступ к ресурсу позиции назначения.
     * Если удалось блокирует ее, а предыдущую позицию разблокирует.
     * @param currentPosition текущая позиция.
     * @param appointPosition позиция назначения.
     * @return true, если удалось сдвинуть, иначе false.
     * */

    public boolean move(Cell currentPosition, Cell appointPosition) throws InterruptedException {
        boolean result = false;
        if (board[appointPosition.getX()][appointPosition.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[appointPosition.getX()][appointPosition.getY()].lock();
            board[currentPosition.getX()][currentPosition.getY()].unlock();
            result = true;
        }
        return result;
    }

    /**
     * lockStartPosition.
     * Блокирует начальную позицию Bomberman'a.
     * @param position позиция.
     * @return true, если удалось, иначе false.
     * */

    public boolean lockStartPosition(Cell position) throws InterruptedException {
        boolean result = false;
        if (board[position.getX()][position.getY()].tryLock(500, TimeUnit.MILLISECONDS)) {
            board[position.getX()][position.getY()].lock();
            result = true;
        }
        return result;
    }
}
