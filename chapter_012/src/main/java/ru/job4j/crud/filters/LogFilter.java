package ru.job4j.crud.filters;

import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.persistent.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogFilter implements Filter {

    private final ValidateService validateService = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = validateService.findByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            chain.doFilter(request, response);
        }
    }
}
