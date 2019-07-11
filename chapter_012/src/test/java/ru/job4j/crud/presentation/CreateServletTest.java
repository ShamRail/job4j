package ru.job4j.crud.presentation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class CreateServletTest {

    @Test
    public void whenAddUserThenStoreIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = PowerMockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = PowerMockito.mock(HttpServletResponse.class);
        PowerMockito.when(req.getParameter("login")).thenReturn("Petr Arsentev");
        new CreateServlet().doPost(req, resp);
        assertThat(validate.findAll().iterator().next().getLogin(), is("Petr Arsentev"));
    }

}