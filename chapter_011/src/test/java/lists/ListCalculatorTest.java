package lists;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import java.util.LinkedList;
import java.util.List;

public class ListCalculatorTest {

    @Test
    public void whenAdd123And123ThenResult246() {
        LinkedList<Integer> num1 = new LinkedList<>();
        LinkedList<Integer> num2 = new LinkedList<>();
        LinkedList<Integer> expected = new LinkedList<>();

        num1.addFirst(1);
        num1.addFirst(2);
        num1.addFirst(3);

        num2.addFirst(1);
        num2.addFirst(2);
        num2.addFirst(3);

        expected.addFirst(2);
        expected.addFirst(4);
        expected.addFirst(6);

        List<Integer> result = new ListCalculator().sum(num1, num2);

        Assert.assertThat(result, Is.is(expected));
    }

    @Test
    public void whenAdd999And11ThenResult1010() {
        LinkedList<Integer> num1 = new LinkedList<>();
        LinkedList<Integer> num2 = new LinkedList<>();
        LinkedList<Integer> expected = new LinkedList<>();

        num1.addFirst(9);
        num1.addFirst(9);
        num1.addFirst(9);

        num2.addFirst(1);
        num2.addFirst(1);

        expected.addFirst(1);
        expected.addFirst(0);
        expected.addFirst(1);
        expected.addFirst(0);

        List<Integer> result = new ListCalculator().sum(num1, num2);

        Assert.assertThat(result, Is.is(expected));
    }

}