package ru.job4j.cinema.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        getterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        setterVisibility        = JsonAutoDetect.Visibility.PUBLIC_ONLY,
        isGetterVisibility      = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC,
        creatorVisibility = JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC)
public class Hall {

    private final boolean[][] hall;

    public Hall(boolean[][] hall) {
        this.hall = hall;
    }

    public boolean[][] getHall() {
        return hall;
    }
}
