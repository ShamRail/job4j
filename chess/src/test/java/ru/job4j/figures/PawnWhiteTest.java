package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.white.PawnWhite;

public class PawnWhiteTest {
    @Test
    public void whenPawnWhiteGoRight() throws ImposibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.A1);
        Cell[] result = pawnWhite.way(Cell.A1, Cell.A2);
        Assert.assertThat(result[0], Is.is(Cell.A2));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenPawnWhiteGoWrong() throws ImposibleMoveException {
        PawnWhite pawnWhite = new PawnWhite(Cell.A1);
        Cell[] result = pawnWhite.way(Cell.A1, Cell.B2);
    }
}