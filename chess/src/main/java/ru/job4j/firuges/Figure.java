package ru.job4j.firuges;

import ru.job4j.exceptions.ImposibleMoveException;

public abstract class Figure {
    /**Позиция фигуры*/
    private final Cell position;
    /**Конструктор*/
    public Figure(Cell position) {
        this.position = position;
    }
    /**
     * position.
     * Метод, возвращает текущую позицию фигуры.
     * @return - позиция.
     * */
    public Cell position() {
        return this.position;
    }
    /**
     * way.
     * Перемещает фигуру.
     * @param currentPosition - текущее положение фигуры.
     * @param appointPosition - позиция назначения.
     * @return путь к назначению.
     * @exception - случай, когда фигура идет неправильно.
     * */
    public abstract Cell[] way(Cell currentPosition, Cell appointPosition) throws ImposibleMoveException;
    /**
     * icon.
     * Метод, возвращает имя картинки.
     * @return имя картинки, которой является фигура.
     * */
    public String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }
    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    public abstract Figure copy(Cell dest);

    /**
     * findByCoordinates.
     * Метод, находящий позицию по координатам.
     * @param x - координата х.
     * @param y - координата у.
     * @return позиция.
     * */
    public Cell findByCoordinates(int x, int y) {
        Cell rst = Cell.A1;
        for (Cell cell : Cell.values()) {
            if (cell.x == x && cell.y == y) {
                rst = cell;
                break;
            }
        }
        return rst;
    }

}
