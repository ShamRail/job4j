package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.KingBlack;

public class KingTest {
    @Test
    public void whenKingGoRightByDiagonale() throws ImposibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.E8);
        Cell[] result = kingBlack.way(Cell.E8, Cell.D7);
        Cell[] expect = {Cell.D7};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test
    public void whenKingGoRightByVerticale() throws ImposibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.E8);
        Cell[] result = kingBlack.way(Cell.E8, Cell.E7);
        Cell[] expect = {Cell.E7};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenKingGoWrong() throws ImposibleMoveException {
        KingBlack kingBlack = new KingBlack(Cell.E8);
        Cell[] result = kingBlack.way(Cell.E8, Cell.E1);
    }
}
