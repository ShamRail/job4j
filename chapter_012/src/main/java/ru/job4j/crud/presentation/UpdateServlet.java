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

public class UpdateServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    private static final Logger LOG = LogManager.getLogger(UpdateServlet.class.getName());


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>Update</title></head>");
        sb.append("<body>");
        sb.append("<h2>Update</h2>");
        sb.append(String.format("<form action='%s/update' method='POST'>", req.getContextPath()));
        sb.append(String.format("<input type='hidden' name='id' value='%s'>", req.getParameter("id")));
        sb.append("<table>");
        sb.append("<tr>");
        sb.append("<td>Login: </td>");
        sb.append(String.format("<td><input type='text' name='login' value='%s'></td>", req.getParameter("login")));
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>Password: </td>");
        sb.append(String.format("<td><input type='text' name='password' value='%s'></td>", req.getParameter("password")));
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>Email: </td>");
        sb.append(String.format("<td><input type='text' name='email' value='%s'></td>", req.getParameter("email")));
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>Date: </td>");
        sb.append(String.format("<td>%s</td>", req.getParameter("date")));
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
        resp.sendRedirect(String.format("%s/list.jsp", req.getContextPath()));
    }
}
