package ru.job4j.crud.filters;

import ru.job4j.crud.persistent.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String loginS = (String) session.getAttribute("login");
        String passwordS = (String) session.getAttribute("password");
        String role = (String) session.getAttribute("role");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (Role.ADMIN.equals(role) || (login.equals(loginS) && password.equals(passwordS))) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        }
    }

}
