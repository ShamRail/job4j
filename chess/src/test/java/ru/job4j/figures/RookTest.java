package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.RookBlack;

public class RookTest {
    @Test
    public void whenRookGoRight() throws ImposibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] result = rookBlack.way(Cell.A8, Cell.A5);
        Cell[] expect = {Cell.A7, Cell.A6, Cell.A5};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenRookGoWrong() throws ImposibleMoveException {
        RookBlack rookBlack = new RookBlack(Cell.A8);
        Cell[] result = rookBlack.way(Cell.A8, Cell.B1);
    }
}
