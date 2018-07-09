package ru.job4j.additionalquestions;

/**
 * Segments.
 * @version 1.0
 * @since 05.07.2018
 * @author Rail Shamsemuhametov.
 */

public class Segments {

    private int a;
    private int b;
    private int c;
    private int d;

    public Segments(int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    /**
     * isSegmentsCrossing.
     * Метод , проверяющий пересекаются ли отрезки.
     * true - пересекаются ,false - нет.
     * @return результат.
     * */

    public boolean isSegmentsCrossing() {
        return (a < c && b < d && c < b)
                || (c < a && d < b && a < d)
                || (a < c && d < b && c < d)
                || (c < a && b < d && a < b);
    }
}
