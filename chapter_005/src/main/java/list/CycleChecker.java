package list;

public class CycleChecker<E> {

    /**
     * hasCycle.
     * Используется алгоритм Флойда.
     * Вводятся два итератора slow, fast.
     * Второй обходит список быстрее чем первый и если он догонит первый значит есть цикл.
     * Есть какой-либо итератор становится нуль, то значит нет циклов.
     * Поиск продолжается до тех пор пока не встретится цикл ил конец списка, что является
     * признаком окончания списка.
     * @param first начало списка.
     * @return результат.
     * */

    public boolean hasCycle(Node<E> first) {
        boolean result = false;
        if (first != null) {
            Node<E> slow = first;
            Node<E> fast = first;
            while (slow != null && fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    result = true;
                    break;
                }

            }
        }
        return result;
    }
}
