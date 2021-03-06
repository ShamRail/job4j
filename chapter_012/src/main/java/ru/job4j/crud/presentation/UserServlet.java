package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.Role;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserServlet extends HttpServlet {

    private final Validate validateService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        try {
            CopyOnWriteArrayList<User> users = validateService.findAll();
            for (User user : users) {
                printWriter.println(user);
            }
        } catch (Exception e) {
            printWriter.println(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            if ("add".equals(action)) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                Role role = new Role(req.getParameter("roles"));
                String country = req.getParameter("country");
                String town = req.getParameter("town");
                User user = new User(id, login, password, email, new Date().toString(), role, country, town);
                validateService.add(user);
                out.println("User is added!");
            } else if ("update".equals(action)) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                String country = req.getParameter("country");
                String town = req.getParameter("town");
                User oldUser = validateService.findById(id);
                Role role = new Role(req.getParameter("roles"));
                User newUser = new User(id, login, password, email, oldUser.getCreateDate(), role, country, town);
                validateService.update(id, newUser);
                out.println("User is updated!");
            } else if ("delete".equals(action)) {
                validateService.delete(id);
                out.println("User is deleted!");
            } else {
                out.println("Invalid action!");
            }
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

}
