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
public class RookBlack extends Figure {
    /**Конструктор.*/
    public RookBlack(Cell apppointPosition) {
        super(apppointPosition);
    }
    /**
     * way.
     * Перемещает фигуру.
     * @param currentPosition - текущее положение фигуры.
     * @param appointPosition - позиция назначения.
     * @return путь к назначению.
     * @exception - случай, когда фигура идет неправильно.
     * */
    @Override
    public Cell[] way(Cell currentPosition, Cell appointPosition) throws ImposibleMoveException {
        if (!new InterimCalculations().isRookCanGo(currentPosition, appointPosition)) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        Cell[] steps = new Cell[appointPosition.x != currentPosition.x ? Math.abs(appointPosition.x - currentPosition.x)
                : Math.abs(appointPosition.y - currentPosition.y)];
        if (appointPosition.x != currentPosition.x) {
            for (int index = 1; index <= steps.length; index++) {
                steps[index - 1] = this.findByCoordinates(appointPosition.x > currentPosition.x ? currentPosition.x + index
                        : currentPosition.x - index, currentPosition.y);
            }
        } else {
            for (int index = 1; index <= steps.length; index++) {
                steps[index - 1] = this.findByCoordinates(currentPosition.x, appointPosition.y > currentPosition.y ? currentPosition.y + index
                        : currentPosition.y - index);
            }
        }
        return steps;
    }
    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    @Override
    public Figure copy(Cell appointPosition) {
        return new RookBlack(appointPosition);
    }
}
