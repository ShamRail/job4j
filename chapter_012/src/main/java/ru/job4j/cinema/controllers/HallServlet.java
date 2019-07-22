package ru.job4j.cinema.controllers;

import ru.job4j.cinema.models.Place;
import ru.job4j.cinema.services.CinemaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HallServlet extends HttpServlet {

    private final CinemaService service = CinemaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/hall.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String place = req.getParameter("place");
        String[] params = place.split("\\.");
        int row = Integer.parseInt(params[0]);
        int col = Integer.parseInt(params[1]);
        try {
            service.inProcess(new Place(row, col, "wait"));
            resp.sendRedirect(String.format("%s/account?row=%s&col=%s", req.getContextPath(), row, col));
        } catch (SQLException e) {
            resp.sendRedirect(String.format("%s/hall", req.getContextPath()));
        }

    }
}
