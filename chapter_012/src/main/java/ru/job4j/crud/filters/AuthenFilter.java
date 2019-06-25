package ru.job4j.crud.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String password = (String) session.getAttribute("login");
        if ("none".equals(password)) {
            resp.sendRedirect(String.format("%s/login", req.getContextPath()));
        } else {
            chain.doFilter(request, response);
        }
    }
}
