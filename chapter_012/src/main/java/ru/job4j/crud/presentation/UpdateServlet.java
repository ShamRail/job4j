package ru.job4j.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class UpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private static final Logger LOG = LogManager.getLogger(UpdateServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        req.setAttribute("login", req.getParameter("login"));
        req.setAttribute("password", req.getParameter("password"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("date", new Date(Long.parseLong(req.getParameter("date"))));
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Updating user ...");
        LOG.debug("Retrieve data");
        int id = Integer.parseInt(req.getParameter("id"));
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(id, login, password, email, System.currentTimeMillis());
        try {
            LOG.debug("Try to update user");
            validateService.update(id, user);
            LOG.debug("User is updated");
        } catch (Exception e) {
            LOG.debug("Failure to update user. {}", e.getMessage());
        }
        LOG.debug("Redirecting to list.jsp");
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
