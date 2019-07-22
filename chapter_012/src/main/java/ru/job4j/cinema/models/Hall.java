package ru.job4j.cinema.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Arrays;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        getterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        setterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        isGetterVisibility      = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
        creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Hall {

    private final String[][] hall;

    public Hall(String[][] hall) {
        this.hall = hall;
    }

    public String[][] getHall() {
        return hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall1 = (Hall) o;
        return Arrays.equals(hall, hall1.hall);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(hall);
    }
}
