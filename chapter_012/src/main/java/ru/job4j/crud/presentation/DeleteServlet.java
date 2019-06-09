package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            validateService.delete(id);
        } catch (Exception e) {
        }
        resp.sendRedirect(String.format("%s/list", req.getContextPath()));
    }
}
