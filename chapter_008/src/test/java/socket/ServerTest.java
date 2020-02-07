package socket;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
public class ServerTest {

    private static final String LN = System.lineSeparator();

    @Test
    public void whenFirstPhraseIsExitThenMustBeEmptyString() throws IOException {
        commonTest("exit", "");
    }

    @Test
    public void whenFirstPhraseIsUnKnown() throws IOException {
        commonTest(
                String.format("How are you?%sexit", LN),
                String.format("I don't understand.%s%s", LN, LN)
        );
    }

    @Test
    public void whenFirstPhraseIsHello() throws IOException {
        commonTest(
                String.format("hello%sexit", LN),
                String.format("Hello, dear friend, I'm a oracle.%s%s", LN, LN)
        );
    }

    private void commonTest(String input, String output) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();

        Assert.assertThat(out.toString(), Is.is(output));
    }
}