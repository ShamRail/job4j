package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.KnightBlack;

public class KnightTest {
    @Test
    public void whenKnightGoRight() throws ImposibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.C8);
        Cell[] result = knightBlack.way(Cell.C8, Cell.D6);
        Assert.assertThat(result[0], Is.is(Cell.D6));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenKnithGoWrong() throws ImposibleMoveException {
        KnightBlack knightBlack = new KnightBlack(Cell.C8);
        Cell[] result = knightBlack.way(Cell.C8, Cell.D5);
    }
}
