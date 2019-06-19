package ru.job4j.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.logic.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private static final Logger LOG = LogManager.getLogger(DeleteServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Deleting user");
        LOG.debug("Retrieve data");
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            LOG.debug("Try to delete user");
            validateService.delete(id);
            LOG.debug("User is deleted");
        } catch (Exception e) {
            LOG.debug("Failure to delete user. {}", e.getMessage());
        }
        LOG.debug("Redirecting to list.jsp");
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
