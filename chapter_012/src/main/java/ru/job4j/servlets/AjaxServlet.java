package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.crud.persistent.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AjaxServlet extends HttpServlet {

    private final List<JSONObject> objects = new CopyOnWriteArrayList<>();

    private static final Logger LOG = LogManager.getLogger(AjaxServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("Prepare object ...");
        Reader reader = req.getReader();
        String json = StringConverter.convert(reader);
        ObjectMapper mapper = new ObjectMapper();
        JSONObject object = mapper.readValue(json, JSONObject.class);
        LOG.debug("Object is created");
        objects.add(object);
        LOG.debug("Object is added");
    }
}
