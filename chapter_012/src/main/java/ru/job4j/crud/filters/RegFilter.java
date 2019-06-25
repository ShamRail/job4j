package ru.job4j.crud.filters;

import ru.job4j.crud.logic.ValidateService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegFilter implements Filter {

    private final ValidateService vs = ValidateService.getInstance();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if ("GET".equalsIgnoreCase(req.getMethod()) || vs.findByLogin(req.getParameter("login")) == null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect(String.format("%s/signup", req.getContextPath()));
        }
    }
}
