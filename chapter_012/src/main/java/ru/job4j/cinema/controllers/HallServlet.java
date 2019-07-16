package ru.job4j.cinema.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HallServlet extends HttpServlet {

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
        resp.sendRedirect(String.format("%s/account?row=%s&col=%s", req.getContextPath(), row, col));
    }
}
