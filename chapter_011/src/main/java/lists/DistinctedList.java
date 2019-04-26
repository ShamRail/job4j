package lists;

import java.util.*;

public class DistinctedList {

    /**
     * Удаляем все дубликаты кроме первого вхождения.
     * */

    public <T> void solution1(LinkedList<T> list) {
        Map<T, LinkedList<Integer>> dupl = findDupl(list);
        LinkedList<Integer> indexes;
        int offset = 0;
        for (Map.Entry<T, LinkedList<Integer>> entry : dupl.entrySet()) {
            indexes = entry.getValue();
            indexes.pollFirst();
            for (Integer index : indexes) {
                list.remove(index - offset);
                offset++;
            }
        }
    }

    public <T> List<T> solution2(LinkedList<T> list) {
        Map<T, LinkedList<Integer>> dupl = findDupl(list);
        List<T> result = new LinkedList<>();
        for (Map.Entry<T, LinkedList<Integer>> entry : dupl.entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    /**
     * Составляет таблицу элемент-список индексов.
     * */

    private <T> Map<T, LinkedList<Integer>> findDupl(List<T> list) {
        Map<T, LinkedList<Integer>> result = new LinkedHashMap<>();
        int index = 0;
        for (T elem : list) {
            if (result.containsKey(elem)) {
                result.get(elem).add(index);
            } else {
                LinkedList<Integer> indexes = new LinkedList<>();
                indexes.addLast(index);
                result.put(elem, indexes);
            }
            index++;
        }
        return result;
    }

}
