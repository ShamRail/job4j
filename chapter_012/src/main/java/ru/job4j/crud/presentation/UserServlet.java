package ru.job4j.crud.presentation;

<<<<<<< HEAD

=======
>>>>>>> 1. Crud servlet, Web app architecture.
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.CopyOnWriteArrayList;

<<<<<<< HEAD

=======
>>>>>>> 1. Crud servlet, Web app architecture.
public class UserServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

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
                User user = new User(id, login, password, email, System.currentTimeMillis());
                validateService.add(user);
                out.println("User is added!");
            } else if ("update".equals(action)) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                User oldUser = validateService.findById(id);
                User newUser = new User(id, login, password, email, oldUser.getCreateDate());
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
