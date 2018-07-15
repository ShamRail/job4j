package strategy;

import org.junit.Test;
import ru.job4j.strategy.Paint;
import ru.job4j.strategy.Square;
import ru.job4j.strategy.Triangle;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Petr Arsentev (mailto:parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PaintTest {
    @Test
    public void whenDrawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square(4));
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    @Test
    public void whenDrawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle(4));
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("*").append(System.lineSeparator())
                                .append("**").append(System.lineSeparator())
                                .append("***").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append(System.lineSeparator())
                                .toString()
                )
        );
        System.setOut(stdout);
    }
}

