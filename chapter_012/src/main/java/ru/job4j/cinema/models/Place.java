package ru.job4j.cinema.models;

import java.util.Objects;

public class Place {

    private final int row;

    private final int col;

    private final String status;

    public Place(int row, int col, String status) {
        this.row = row;
        this.col = col;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String status() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return row == place.row &&
                col == place.col &&
                status.equals(place.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, status);
    }
}
