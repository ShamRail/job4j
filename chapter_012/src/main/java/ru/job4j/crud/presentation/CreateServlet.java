package ru.job4j.crud.presentation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private static final Logger LOG = LogManager.getLogger(CreateServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>Create</title></head>");
        sb.append("<body>");
        sb.append("<h2>Create</h2>");
        sb.append(String.format("<form action='%s/create' method='POST'>", req.getContextPath()));
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<td>Login: </td>");
        sb.append("<td><input type='text' name='login'></td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>Password: </td>");
        sb.append("<td><input type='text' name='password'></td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>Email: </td>");
        sb.append("<td><input type='text' name='email'></td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td></td>");
        sb.append("<td><input type='submit' value='Save'></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");
        resp.getWriter().println(sb.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Creating user ...");
        LOG.debug("Retrieve data");
        int id = validateService.getMaxID() + 1;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        long date = System.currentTimeMillis();
        User user = new User(id, login, password, email, date);
        try {
            LOG.debug("Try to add user");
            validateService.add(user);
            LOG.debug("User is added");
        } catch (ValidationException e) {
            LOG.debug("Failure to add user. {}", e.getMessage());
        }
        LOG.debug("Redirecting to list.jsp");
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }
}
