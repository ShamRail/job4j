package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.Role;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class SignupServlet extends HttpServlet {

    private final Validate validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Role role = new Role(req.getParameter("roles"));
        String date = new Date().toString();
        String country = req.getParameter("country");
        String town = req.getParameter("town");
        int id = validateService.getMaxID() + 1;
        User user = new User(id, login, password, email, date, role, country, town);
        try {
            validateService.add(user);
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("role", req.getParameter("roles"));
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        } catch (ValidationException e) {
            resp.sendRedirect(String.format("%s/signup", req.getContextPath()));
        }
    }
}
