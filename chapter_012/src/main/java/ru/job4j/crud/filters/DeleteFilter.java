package ru.job4j.crud.filters;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFilter implements Filter {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        String loginS = (String) session.getAttribute("login");
        String passwordS = (String) session.getAttribute("password");
        try {
            User user = validateService.findById(Integer.parseInt(req.getParameter("id")));
            String userLogin  = user.getLogin();
            String userPassword = user.getPassword();
            if (userLogin.equals(loginS) && userPassword.equals(passwordS)) {
                session.setAttribute("login", "none");
                session.setAttribute("password", "none");
                session.setAttribute("role", "");
                validateService.delete(user.getId());
                resp.sendRedirect(String.format("%s/login", req.getContextPath()));
            } else if ("admin".equals(role)) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(String.format("%s/list", req.getContextPath()));
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }
}
