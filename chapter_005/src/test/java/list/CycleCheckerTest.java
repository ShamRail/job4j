package list;

import org.junit.Test;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

public class CycleCheckerTest {

    CycleChecker<Integer> cycleChecker = new CycleChecker<>();

    Node<Integer> first = new Node<>(1);
    Node<Integer> two = new Node<>(2);
    Node<Integer> third = new Node<>(3);
    Node<Integer> four = new Node<>(4);

    @Test
    public void whenInvokeHasCycleForNoLinkedNodeThenMustReturnFalse() {
        Node<Integer> node = new Node<>(1);
        assertThat(cycleChecker.hasCycle(node), is(false));
    }

    @Test
    public void whenInvokeHasCycleForNodesWithoutCyclesMustReturnFalse() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(cycleChecker.hasCycle(first), is(false));
    }

    @Test
    public void whenCycleOnFirstElementThenReturnTrue() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(cycleChecker.hasCycle(first), is(true));
    }

    @Test
    public void whenCycledOnPreviousElement() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = third;
        assertThat(cycleChecker.hasCycle(first), is(true));
    }

    @Test
    public void whenCycleAtBeginning() {
        first.next = two;
        two.next = first;
        third.next = four;
        four.next = third;
        assertThat(cycleChecker.hasCycle(first), is(true));
    }

    @Test
    public void whenCycleOnMyself() {
        first.next = two;
        two.next = two;
        assertThat(cycleChecker.hasCycle(first), is(true));

    }
}