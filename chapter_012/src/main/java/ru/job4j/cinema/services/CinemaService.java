package ru.job4j.cinema.services;

import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.Hall;
import ru.job4j.cinema.models.Place;

import java.sql.SQLException;

public class CinemaService {

    private final static AccountsDAO ACCOUNTS = AccountsDAO.getInstance();

    private final static HallDAO HALL = HallDAO.getInstance();

    private final static CinemaService INSTANCE = new CinemaService();

    private CinemaService() {

    }

    public static CinemaService getInstance() {
        return INSTANCE;
    }

    public void addAccount(Account account) {
        ACCOUNTS.create(account);
    }

    public Hall getHall() {
        return HALL.getHall();
    }

    public void occupy(Place place) throws SQLException {
        HALL.occupy(place);
    }

    public void inProcess(Place place) throws SQLException {
        HALL.inProcess(place);
    }

    public void free(Place place) throws SQLException {
        HALL.free(place);
    }

}
