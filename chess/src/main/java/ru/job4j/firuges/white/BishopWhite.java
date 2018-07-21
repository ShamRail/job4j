package ru.job4j.firuges.white;

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
public class BishopWhite extends Figure {
    /**Конструктор.*/
    public BishopWhite(Cell apppointPosition) {
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
        if (!new InterimCalculations().isBishopCanGo(currentPosition, appointPosition)) {
            throw new ImposibleMoveException("Фигура так не ходит!");
        }
        Cell[] steps = new Cell[Math.abs(currentPosition.x - appointPosition.x)];
        for (int index = 1; index <= steps.length; index++) {
            int x = appointPosition.x > currentPosition.x ? currentPosition.x + index : currentPosition.x - index;
            int y = appointPosition.y > currentPosition.y ? currentPosition.y + index : currentPosition.y - index;
            steps[index - 1] = this.findByCoordinates(x, y);
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
        return new BishopWhite(appointPosition);
    }
}
