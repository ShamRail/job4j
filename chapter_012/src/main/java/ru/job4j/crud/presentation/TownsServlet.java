package ru.job4j.crud.presentation;

import ru.job4j.crud.persistent.TownsDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TownsServlet extends HttpServlet {

    private static final TownsDAO DAO = TownsDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String param = StringConverter.convert(req.getReader());
        String param = req.getParameter("type");
        List<String> locality;
        if ("towns".equals(param)) {
            String cntr = req.getParameter("country");
            locality = DAO.getTowns(cntr);
        } else {
            locality = DAO.getCountries();
        }
        StringBuilder sb = new StringBuilder("");
        for (String str : locality) {
            sb.append(str);
            sb.append("|");
        }
        String rslt = sb.toString().substring(0, sb.lastIndexOf("|"));
        resp.getWriter().print(rslt);
    }
}
