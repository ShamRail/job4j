package ru.job4j;

import ru.job4j.exceptions.FigureNotFoundException;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.exceptions.OccupiedWayException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.Figure;
import java.util.Arrays;
import java.util.function.Predicate;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    /**Фигуры*/
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    /**
     * add.
     * Метод, добавляющий фигуры в массив фигур.
     * @param figure - фигура.
     * */

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    /**
     * move.
     * Метод, двигаюший фигуру, если это возможно.
     * true - возможно, false - невозможно.
     * @param source - текущее положение фигуры.
     * @param dest - назначение фигуры.
     * @return - результат.
     * @exception ImposibleMoveException - случай когда фигура идет неверно.
     * @exception FigureNotFoundException - случай когда фигура не найдена.
     * @exception OccupiedWayException -  случай когда позиция уже занята.
     * */

    //
    public boolean move(Cell source, Cell dest) throws ImposibleMoveException, FigureNotFoundException, OccupiedWayException {
        boolean rst = false;
        if (!dest.equals(source)) {
            int index = this.findByPosition(source);
            if (index == -1) {
                throw new FigureNotFoundException("Фигура не найдена!");
            }
            if (this.findByPosition(dest) != -1) {
                throw new OccupiedWayException("На этом месте уже стоит фигура!");
            }
            Cell[] steps = this.figures[index].way(source, dest);
            if (this.occupaidWay(steps)) {
                throw new ImposibleMoveException("Фигура так не ходит!");
            }
            if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                rst = true;
                this.figures[index] = this.figures[index].copy(dest);
            }
        }
        return rst;
    }

    /**
     * clean.
     * Ощищает массив фигур.
     * */

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    /**
     * findByPosition.
     * Находит индекс фигуры в массиве фигур по ее позиции.
     * @return индекс фигуры в массиве.
     * */
    private int findByPosition(Cell position) {
        int rst = -1;
        Predicate<Cell> predicate = (cell -> cell.equals(position));
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && predicate.test(this.figures[index].position())) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    /**
     * occupaindWay.
     * Метод, проверяющий есть на пути другие фигуры.
     * @param way - путь.
     * @return результат.
     * */
    private boolean occupaidWay(Cell[] way) {
        return Arrays.stream(way).anyMatch((cell -> this.findByPosition(cell) != -1));
    }
}
