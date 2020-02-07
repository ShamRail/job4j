package ru.job4j.crud.presentation;

import org.hamcrest.core.Is;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.logic.ValidateService;
import ru.job4j.crud.logic.ValidationException;
import ru.job4j.crud.persistent.Role;
import ru.job4j.crud.persistent.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
@Ignore
public class UpdateServletTest {

    @Test
    public void test() throws ValidationException, ServletException, IOException {
        Validate validate = new ValidateStub();
        User user = new User(
                0, "log", "pass", "email", "123", new Role("user"), "", ""
        );
        validate.add(user);
        PowerMockito.mockStatic(ValidateService.class);
        PowerMockito.when(ValidateService.getInstance()).thenReturn(validate);
        HttpServletRequest req = PowerMockito.mock(HttpServletRequest.class);
        HttpServletResponse resp = PowerMockito.mock(HttpServletResponse.class);
        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("login")).thenReturn("log2");
        new UpdateServlet().doPost(req, resp);
        assertThat(validate.findById(0).getLogin(), Is.is("log2"));
    }

}