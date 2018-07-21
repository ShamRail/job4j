package ru.job4j.figures;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.ImposibleMoveException;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.QeenBlack;

public class QueenTest {
    @Test
    public void whenQueenGoRightByHorizontal() throws ImposibleMoveException {
        QeenBlack qeenBlack = new QeenBlack(Cell.D8);
        Cell[] result = qeenBlack.way(Cell.D8, Cell.D6);
        Cell[] expect = {Cell.D7, Cell.D6};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test
    public void whenQueenGoRightByDiagonale() throws ImposibleMoveException {
        QeenBlack qeenBlack = new QeenBlack(Cell.D8);
        Cell[] result = qeenBlack.way(Cell.D8, Cell.F6);
        Cell[] expect = {Cell.E7, Cell.F6};
        Assert.assertThat(result, Is.is(expect));
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenQueenGoWrong() throws ImposibleMoveException {
        QeenBlack qeenBlack = new QeenBlack(Cell.D8);
        Cell[] result = qeenBlack.way(Cell.D8, Cell.E6);
    }
}
