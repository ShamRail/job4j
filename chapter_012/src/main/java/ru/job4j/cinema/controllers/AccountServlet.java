package ru.job4j.cinema.controllers;

import ru.job4j.cinema.models.Account;
import ru.job4j.cinema.models.Place;
import ru.job4j.cinema.services.CinemaService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AccountServlet extends HttpServlet {

    private final CinemaService cinema = CinemaService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("row", new Integer(req.getParameter("row")));
        req.setAttribute("col", new Integer(req.getParameter("col")));
        req.getRequestDispatcher("/WEB-INF/views/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String operation = req.getParameter("operation");
        int row = Integer.parseInt(req.getParameter("row"));
        int col = Integer.parseInt(req.getParameter("col"));
        try {
            if ("free".equals(operation)) {
                cinema.free(new Place(row, col, "free"));
            } else {
                String name = req.getParameter("name");
                String telNumber = req.getParameter("tel_num");
                Account account = new Account(name, telNumber, new Place(row, col, "busy"));
                cinema.occupy(account.getPlace());
                cinema.addAccount(account);
            }
        } catch (SQLException e) {
            resp.sendRedirect(String.format("%s/hall", req.getContextPath()));
        }
        resp.sendRedirect(String.format("%s/hall", req.getContextPath()));
    }
}
