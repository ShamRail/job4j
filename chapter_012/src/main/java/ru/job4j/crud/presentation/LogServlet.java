package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogServlet extends HttpServlet {

    private final Validate validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        User user = validateService.findByLogin(login);
        HttpSession session = req.getSession();
        session.setAttribute("login", user.getLogin());
        session.setAttribute("password", user.getPassword());
        session.setAttribute("role", user.getRole().getRole());
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
