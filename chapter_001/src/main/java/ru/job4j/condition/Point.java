package ru.job4j.condition;

/**
 * Point
 * @version 1.0
 * @since 05.07.2018
 * @author Rail Shamsemukhametov
 * */

public class Point {
    /**x contains x coordinate*/
    private int x;
    /**y contains y coordinate*/
    private int y;

    /**
     * Constructor
     * */

    public  Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /***
     * distanceTo
     * @param Point Second Point
     * @return distance between Points
     */

    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }
}
