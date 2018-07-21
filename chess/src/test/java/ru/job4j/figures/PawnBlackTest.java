package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.PawnBlack;

public class PawnBlackTest {
    @Test
    public void whenPawnBlackGoRight() throws ImposibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.A7);
        Cell[] result = pawnBlack.way(Cell.A7, Cell.A6);
        Assert.assertThat(result[0], Is.is(Cell.A6));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenPawnBlackGoWrong() throws ImposibleMoveException {
        PawnBlack pawnBlack = new PawnBlack(Cell.A7);
        Cell[] result = pawnBlack.way(Cell.A7, Cell.B5);
    }
}
