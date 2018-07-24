package ru.job4j.firuges.black;

import ru.job4j.InterimCalculations;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class KingBlack extends Figure {
    /**Конструктор.*/
    public KingBlack(Cell apppointPosition) {
        super(apppointPosition);
    }
    /**
     * way.
     * Перемещает фигуру.
     * @param currentPosition - текущее положение фигуры.
     * @param appointPosition - позиция назначения.
     * @return путь к назначению.
     * */
    @Override
    public Cell[] way(Cell currentPosition, Cell appointPosition) throws ImposibleMoveException {
        if (!new InterimCalculations().isKingCanGo(currentPosition, appointPosition)) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        Cell[] steps = new Cell[1];
        int x = appointPosition.x;
        int y = appointPosition.y;
        if (appointPosition.x != currentPosition.x) {
            x = appointPosition.x > currentPosition.x ? currentPosition.x + 1 : currentPosition.x - 1;
        }
        if (appointPosition.y != currentPosition.y) {
            y = appointPosition.y > currentPosition.y ? currentPosition.y + 1 : currentPosition.y - 1;
        }
        steps[0] = this.findByCoordinates(x, y);
        return steps;
    }
    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    @Override
    public Figure copy(Cell appointPosition) {
        return new KingBlack(appointPosition);
    }
}