package ru.job4j;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exceptions.*;
import ru.job4j.firuges.Cell;
import ru.job4j.firuges.black.PawnBlack;
import ru.job4j.firuges.black.RookBlack;

public class LogicTest {

    private Logic simulateChess() {
        Logic logic = new Logic();
        PawnBlack pawnBlack = new PawnBlack(Cell.A7);
        RookBlack rookBlack = new RookBlack(Cell.A8);
        logic.add(pawnBlack);
        logic.add(rookBlack);
        return logic;
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenThrownsFigureNotFoundException() throws FigureNotFoundException, OccupiedWayException, ImposibleMoveException {
        Logic logic = this.simulateChess();
        logic.move(Cell.A6, Cell.A8);
    }
    @Test(expected = OccupiedWayException.class)
    public void whenThrownsOccupiedWayException() throws FigureNotFoundException, OccupiedWayException, ImposibleMoveException {
        Logic logic = this.simulateChess();
        logic.move(Cell.A8, Cell.A7);
    }
    @Test(expected = ImposibleMoveException.class)
    public void whenThrownsImposibleMoveException() throws FigureNotFoundException, OccupiedWayException, ImposibleMoveException {
        Logic logic = this.simulateChess();
        logic.move(Cell.A8, Cell.B7);
    }
    @Test
    public void whenAllRight() throws FigureNotFoundException, OccupiedWayException, ImposibleMoveException {
        Logic logic = this.simulateChess();
        boolean result = logic.move(Cell.A8, Cell.H8);
        Assert.assertThat(result, Is.is(true));
    }
}
