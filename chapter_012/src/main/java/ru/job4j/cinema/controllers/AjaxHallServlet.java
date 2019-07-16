package ru.job4j.cinema.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.models.Hall;
import ru.job4j.cinema.services.CinemaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxHallServlet extends HttpServlet {

    private final CinemaService cinemaService = CinemaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Hall hall = cinemaService.getHall();
        String json = mapper.writeValueAsString(hall);
        resp.getWriter().print(json);
    }
}
