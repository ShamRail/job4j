package strategy;

import org.junit.Test;
import ru.job4j.strategy.Square;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SquareTest {
    @Test
    public void whenDrawSquare() {
        Square square = new Square(4);
        assertThat(
                square.draw(),
                is(
                        new StringBuilder()
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .append("****").append(System.lineSeparator())
                                .toString()
                )
        );
    }
}
