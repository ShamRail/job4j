package ru.job4j.loop;

/**
 * Paint.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 */

public class Paint {

    /**
     * pyramid.
     * Метод , строящий пирамиду в псевдографике. weight - ширина пирамиды.
     * row - строка , column - столбец.
     * @param height - высота пирамиды.
     * @return рисунок пирамиды.
     * */

    public String pyramid(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = 2 * height - 1;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= height - column - 1 && row + height - 1 >= column) {
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
