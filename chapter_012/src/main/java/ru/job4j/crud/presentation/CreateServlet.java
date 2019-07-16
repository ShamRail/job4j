package ru.job4j.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.Role;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CreateServlet extends HttpServlet {

    private final Validate validateService = ValidateService.getInstance();

    private static final Logger LOG = LogManager.getLogger(CreateServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Creating user ...");
        LOG.debug("Retrieve data");
        int id = validateService.getMaxID() + 1;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        Role role = new Role(req.getParameter("roles"));
        String country = req.getParameter("country");
        String town = req.getParameter("town");
        Date date = new Date();
        User user = new User(id, login, password, email, date.toString(), role, country, town);
        try {
            LOG.debug("Try to add user");
            validateService.add(user);
            LOG.debug("User is added");
        } catch (ValidationException e) {
            LOG.debug("Failure to add user. {}", e.getMessage());
        }
        LOG.debug("Redirecting to list.jsp");
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
