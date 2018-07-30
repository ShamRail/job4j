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
public class QeenBlack extends Figure {
    /**Конструктор.*/
    public QeenBlack(Cell apppointPosition) {
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
        if (!new InterimCalculations().isQueenCanGo(currentPosition, appointPosition)) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        Cell[] way = new Cell[currentPosition.x == appointPosition.x ? Math.abs(appointPosition.y - currentPosition.y)
                : Math.abs(appointPosition.x - currentPosition.x)];
        InterimCalculations.fillSteps(this, way, currentPosition, appointPosition);
        return way;
    }

    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    @Override
    public Figure copy(Cell appointPosition) {
        return new QeenBlack(appointPosition);
    }
}
