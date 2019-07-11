package ru.job4j.crud.filters;

import ru.job4j.crud.persistent.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (Role.ADMIN.equals(role)) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(String.format("%s/list", req.getContextPath()));
        }
    }

}
