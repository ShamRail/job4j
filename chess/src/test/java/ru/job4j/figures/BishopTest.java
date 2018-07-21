package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.BishopBlack;

public class BishopTest {
    @Test
    public void whenBishopGoRight() throws ImposibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] result = bishopBlack.way(Cell.C8, Cell.E6);
        Cell[] expect = {Cell.D7, Cell.E6};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenBishopGoWrong() throws ImposibleMoveException {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] result = bishopBlack.way(Cell.C8, Cell.D8);
    }
}
