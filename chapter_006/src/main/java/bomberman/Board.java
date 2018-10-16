package bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {

    private final int size;

    private final ReentrantLock[][] board;

    private final Thread[] monsters;

    private final int blockedBlocks;

    public Board(int boardSize, int monstersCount, int blockedBlocks) {
        size = boardSize;
        this.blockedBlocks = blockedBlocks;
        this.board = new ReentrantLock[size][size];
        this.monsters = new Thread[monstersCount];
        this.fillBoard();
        this.fillBlocks();
        this.fillMonsters();
    }
    /**
     * fillBoard.
     * Иницилизирует доску объектами ReentrantLock.
     * */

    private void fillBoard() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                board[row][column] = new ReentrantLock();
            }
        }
    }

    /**
     * fillBlocks.
     * Блокирует ячейки доски случайным образом.
     * */

    private void fillBlocks() {
        int x;
        int y;
        boolean isBlocked = false;
        for (int i = 0; i < blockedBlocks; i++) {
            do {
                x = (int) (Math.random() * size);
                y = (int) (Math.random() * size);
                if (board[x][y].tryLock()) {
                    board[x][y].lock();
                    isBlocked = true;
                }
            } while (!isBlocked);
        }
    }

    /**
     * fillMonsters.
     * Заполняет доску монстрами.
     * */

    private void fillMonsters() {
        for (int i = 0; i < monsters.length; i++) {
            Monster monster = new Monster(this);
            Thread threadMonster = new Thread(monster);
            threadMonster.setName(String.format("Monster %s", i + 1));
            monsters[i] = threadMonster;
        }
    }

    /**
     * startMonsters.
     * Запускает движение монстров.
     * */

    public void startMonsters() {
        for (Thread monster : monsters) {
            monster.start();
        }
    }

    /**
     * move.
     * Двигает персонажей.
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
     * Блокирует начальную позицию персонажей.
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

    public int getSize() {
        return this.size;
    }
}
