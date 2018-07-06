package ru.job4j.loop;

/**
 * Board.
 * @version 1.0.
 * @since 06.07.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Board {

    /**
     * paint
     * Метод , строящий шахматную доску в псевдографике.
     * @param width - ширина доски.
     * @param height - высота доски.
     * @return рисунок доски.
     * */

    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}