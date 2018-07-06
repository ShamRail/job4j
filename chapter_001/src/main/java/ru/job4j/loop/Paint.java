package ru.job4j.loop;
import java.util.function.BiPredicate;

/**
 * Paint.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 */

public class Paint {

    /**
     * rightTrl.
     * Метод , строящий пирамиду в псевдографике справа.
     * row - строка , column - столбец.
     * @param height - высота пирамиды.
     * @return рисунок пирамиды.
     * */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * leftTrl.
     * Метод , строящий пирамиду в псевдографике слева.
     * row - строка , column - столбец.
     * @param height - высота пирамиды.
     * @return рисунок пирамиды.
     * */

    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }
    /**
     * pyramid.
     * Метод , строящий пирамиду в псевдографике.
     * row - строка , column - столбец.
     * @param height - высота пирамиды.
     * @return рисунок пирамиды.
     * */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }
    /**
     * loopBy.
     * Метод , строящий пирамиду в псевдографике.
     * row - строка , column - столбец.
     * @param  weight - ширина пирамиды
     * @param height - высота пирамиды.
     * @return рисунок пирамиды.
     * */
    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
