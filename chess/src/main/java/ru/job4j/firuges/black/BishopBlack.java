package ru.job4j.firuges.black;

import ru.job4j.InterimCalculations;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.Figure;
import ru.job4j.firuges.white.BishopWhite;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack extends Figure {
    /**Конструктор.*/
    public BishopBlack(Cell apppointPosition) {
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
        if (!new InterimCalculations().isBishopCanGo(currentPosition, appointPosition)) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        Cell[] steps = new Cell[Math.abs(currentPosition.x - appointPosition.x)];
        InterimCalculations.fillSteps(this, steps, currentPosition, appointPosition);
        return steps;
    }
    /**
     * copy.
     * Возвращает новую фигуру по позиции.
     * @return - фигура.
     * */
    @Override
    public Figure copy(Cell appointPosition) {
        return new BishopBlack(appointPosition);
    }
}
