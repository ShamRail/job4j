package ru.job4j;

import ru.job4j.firuges.Cell;
import ru.job4j.firuges.Figure;

/**
 * InterimCalculations.
 * Класс, описывающий могут ли фигуры ходить.
 * @version 1.0
 * @since 20.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class InterimCalculations {
    /**
     * findDelta.
     * Возвращает дельту движения по координате.
     * 1 - вправо(вверх), -1 - влево(вниз), 0 - не двигаться.
     * @param currentCoordinatePosition текущая координата.
     * @param appointCoordinatePosition координата назначения.
     * @return результат.
     * */
    private int findDelta(int currentCoordinatePosition, int appointCoordinatePosition) {
        return Integer.compare(appointCoordinatePosition, currentCoordinatePosition);
    }
    /**
     * fillSteps.
     * Заполняет путь движения фигуры.
     * @param figure фигура.
     * @param steps массив шагов.
     * @param currentPosition текущее положение фигуры.
     * @param appointPosition назначение фигуры.
     * */
    public void fillSteps(Figure figure, Cell[] steps, Cell currentPosition, Cell appointPosition) {
        int deltaX = this.findDelta(currentPosition.x, appointPosition.x);
        int deltaY = this.findDelta(currentPosition.y, appointPosition.y);
        int x = currentPosition.x + deltaX;
        int y = currentPosition.y + deltaY;
        for (int index = 0; index < steps.length; index++) {
            steps[index] = figure.findByCoordinates(x, y);
            x += deltaX;
            y += deltaY;
        }
    }
    /**
     * isBishopCanGo.
     * Описывает может ли ходить слон.
     * @param currentPosition - текущая позиция.
     * @param appointPosition - позиция назначения.
     * @return результат.
     * */
    public boolean isBishopCanGo(Cell currentPosition, Cell appointPosition) {
        boolean result = false;
        for (int delta = 0; delta < 8; delta++) {
            if (
                            currentPosition.x - delta == appointPosition.x && currentPosition.y - delta == appointPosition.y
                            || currentPosition.x - delta == appointPosition.x && currentPosition.y + delta == appointPosition.y
                            || currentPosition.x + delta == appointPosition.x && currentPosition.y - delta == appointPosition.y
                            || currentPosition.x + delta == appointPosition.x && currentPosition.y + delta == appointPosition.y
                    ) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * isKnightCanGo.
     * Описывает может ли ходить конь.
     * @param currentPosition - текущая позиция.
     * @param appointPosition - позиция назначения.
     * @return результат.
     * */

    public boolean isKnightCanGo(Cell currentPosition, Cell appointPosition) {
        return currentPosition.x - 1 == appointPosition.x && currentPosition.y - 2 == appointPosition.y
                || currentPosition.x - 1 == appointPosition.x && currentPosition.y + 2 == appointPosition.y
                || currentPosition.x + 1 == appointPosition.x && currentPosition.y - 2 == appointPosition.y
                || currentPosition.x + 1 == appointPosition.x && currentPosition.y + 2 == appointPosition.y
                || currentPosition.x + 2 == appointPosition.x && currentPosition.y - 1 == appointPosition.y
                || currentPosition.x + 2 == appointPosition.x && currentPosition.y + 1 == appointPosition.y
                || currentPosition.x - 2 == appointPosition.x && currentPosition.y - 1 == appointPosition.y
                || currentPosition.x - 2 == appointPosition.x && currentPosition.y + 1 == appointPosition.y;
    }
    /**
     * isRooktCanGo.
     * Описывает может ли ходить ладья.
     * @param currentPosition - текущая позиция.
     * @param appointPosition - позиция назначения.
     * @return результат.
     * */
    public boolean isRookCanGo(Cell currentPosition, Cell appointPosition) {
        return currentPosition.x == appointPosition.x || currentPosition.y == appointPosition.y;
    }
    /**
     * isQueenCanGo.
     * Описывает может ли ходить ферзь.
     * @param currentPosition - текущая позиция.
     * @param appointPosition - позиция назначения.
     * @return результат.
     * */
    public boolean isQueenCanGo(Cell currentPosition, Cell appointPosition) {
        return this.isBishopCanGo(currentPosition, appointPosition) || this.isRookCanGo(currentPosition, appointPosition);
    }
    /**
     * isKingCanGo.
     * Описывает может ли ходить король.
     * @param currentPosition - текущая позиция.
     * @param appointPosition - позиция назначения.
     * @return результат.
     * */
    public boolean isKingCanGo(Cell currentPosition, Cell appointPosition) {
        return currentPosition.x == appointPosition.x && currentPosition.y + 1 == appointPosition.y
                || currentPosition.x == appointPosition.x && currentPosition.y - 1 == appointPosition.y
                || currentPosition.x + 1 == appointPosition.x && currentPosition.y == appointPosition.y
                || currentPosition.x + 1 == appointPosition.x && currentPosition.y + 1 == appointPosition.y
                || currentPosition.x + 1 == appointPosition.x && currentPosition.y - 1 == appointPosition.y
                || currentPosition.x - 1 == appointPosition.x && currentPosition.y == appointPosition.y
                || currentPosition.x - 1 == appointPosition.x && currentPosition.y + 1 == appointPosition.y
                || currentPosition.x - 1 == appointPosition.x && currentPosition.y - 1 == appointPosition.y;
    }

}
