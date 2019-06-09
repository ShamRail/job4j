package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReadServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head><title>List</title></head>");
        sb.append("<body>");
        sb.append("<h2>Users</h2>");
        sb.append("<table border='1'>");
        sb.append("<tr><td>ID</td><td>Login</td><td>Password</td><td>Email</td><td>Date</td></tr>");
        try {
            System.out.println("Try to retrieve users");
            CopyOnWriteArrayList<User> list = validateService.findAll();
            System.out.println("Users are retrieved");
            for (User user : list) {
                sb.append("<tr>");
                sb.append(String.format("<td>%s</td>", user.getId()));
                sb.append(String.format("<td>%s</td>", user.getLogin()));
                sb.append(String.format("<td>%s</td>", user.getPassword()));
                sb.append(String.format("<td>%s</td>", user.getEmail()));
                sb.append(String.format("<td>%s</td>", new Date(user.getCreateDate())));
                sb.append("<td>");
                sb.append(String.format("<form action='%s/update' method='GET'>", req.getContextPath()));
                sb.append(String.format("<input type='hidden' name='id' value='%s'>", user.getId()));
                sb.append(String.format("<input type='hidden' name='login' value='%s'>", user.getLogin()));
                sb.append(String.format("<input type='hidden' name='password' value='%s'>", user.getPassword()));
                sb.append(String.format("<input type='hidden' name='email' value='%s'>", user.getEmail()));
                sb.append(String.format("<input type='hidden' name='date' value='%s'>", new Date(user.getCreateDate())));
                sb.append("<input type='submit' value='Edit'>");
                sb.append("</form>");
                sb.append("</td>");
                sb.append("<td>");
                sb.append(String.format("<form action='%s/delete' method='POST'>", req.getContextPath()));
                sb.append(String.format("<input type='hidden' name='id' value='%s'>", user.getId()));
                sb.append("<input type='submit' value='Delete'>");
                sb.append("</form>");
                sb.append("</td>");
                sb.append("</tr>");
            }
        } catch (ValidationException e) {
            out.println();
        }
        sb.append("</table>");
        sb.append(String.format("<form action='%s/create' method='GET'>", req.getContextPath()));
        sb.append("<input type='submit' value='Create'>");
        sb.append("</form>");
        sb.append("</body>");
        sb.append("</html>");
        out.println(sb.toString());
    }
}
