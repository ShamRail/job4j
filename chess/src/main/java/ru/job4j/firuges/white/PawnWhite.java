package ru.job4j.firuges.white;

import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.Figure;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite extends Figure {
    /**Конструктор.*/
    public PawnWhite(Cell apppointPosition) {
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
        if (currentPosition.x != appointPosition.x || Math.abs(currentPosition.y - appointPosition.y) > 1) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        return (currentPosition.y + 1 == appointPosition.y) ? new Cell[] {appointPosition} : new Cell[0];
    }
    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    @Override
    public Figure copy(Cell appointPosition) {
        return new PawnWhite(appointPosition);
    }
}
