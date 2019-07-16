package ru.job4j.cinema.models;

public class Place {

    private final int row;

    private final int col;

    private final boolean isBusy;

    public Place(int row, int col, boolean isBusy) {
        this.row = row;
        this.col = col;
        this.isBusy = isBusy;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isBusy() {
        return isBusy;
    }
}
