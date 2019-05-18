package graphs;

import java.util.LinkedList;

public class MinWay {

    /***
     * Алгоритм Дейкстры (находит минимальные пути до каждой вершины).
     * Заводим вспомогательных массива:
     * 1) mark массив меток. Нужен т.к. в пути не может быть одинаковых вершин.
     * 2) parents массив предков. i-й элемент хранит предшествующую до него в пути вершину.
     * 3) distance. i-й элемент хранит длину пути до себя.
     * Выбираем минимальную не помеченную вершину. Находим пути через эту вершину.
     * Делаем это для каждой вершины.
     * @param graph исходный граф.
     * @return массив предков.
     */

    private int[] deikstra(int[][] graph) {
        boolean[] mark = new boolean[graph.length];
        int[] parents = new int[graph.length];
        int[] distance = new int[graph.length];
        mark[0] = true;
        for (int i = 0; i < graph.length; i++) {
            distance[i] = graph[0][i];
        }
        for (int i = 0; i < graph.length; i++) {
            int indexOfMin = indexOfMin(distance, mark);
            mark[indexOfMin] = true;
            for (int j = 0; j < graph.length; j++) {
                if (!mark[j]
                        && (distance[j] < Integer.MAX_VALUE || graph[indexOfMin][j] < Integer.MAX_VALUE)
                        && graph[indexOfMin][j] != Integer.MAX_VALUE
                        && (distance[indexOfMin] + graph[indexOfMin][j] < distance[j])) {
                            distance[j] = distance[indexOfMin] + graph[indexOfMin][j];
                            parents[j] = indexOfMin;
                }
            }
        }
        return parents;
    }

    /***
     * Находит минимальную не помеченную вершину.
     * @param distance длины.
     * @param mark массив меток.
     * @return индекс этого элемента.
     */

    private int indexOfMin(int[] distance, boolean[] mark) {
        int indexOfMin = 0;
        for (int j = 0; j < distance.length; j++) {
            if (distance[j] != 0 && !mark[j]) {
                indexOfMin = j;
                break;
            }
        }
        for (int j = indexOfMin + 1; j < distance.length; j++) {
            if (!mark[j] && distance[j] < distance[indexOfMin]) {
                indexOfMin = j;
            }
        }
        return indexOfMin;
    }

    /***
     * Востанавливает путь используя массив предков.
     * Путь разбивается на две части в случае если путь проходит через 0 вершину.
     * (5-0-1). Иначе просто ищем от конечной вершины до начальной.
     * @param graph исходный график.
     * @param number1 первая вершина.
     * @param number2 вторая вершина.
     * @return путь от number1 до number2.
     */

    public LinkedList<Integer> restoreWay(int[][] graph, int number1, int number2) {
        int[] parents = deikstra(graph);
        int end = number2;
        int begin = number1;
        LinkedList<Integer> result = new LinkedList<>();
        boolean isFound = false;
        while (end != begin && !isFound) {
            result.add(end);
            end = parents[end];
            isFound = result.getLast() == number1;
        }
        if (isFound) {
            LinkedList<Integer> additionalWay = new LinkedList<>();
            begin = 0;
            end = number1;
            while (begin != end) {
                additionalWay.add(end);
                end = parents[end];
            }
            result.addAll(reverse(additionalWay));
        } else {
            result.add(number1);
        }
        return reverse(result);
    }

    /***
     * Переворачивает список.
     * Эта функция необходима, т.к. при восстановлении пути мы идем с конца.
     * @param additionalWay переворачиваемый список.
     * @return перевернутый список.
     */

    private LinkedList<Integer> reverse(LinkedList<Integer> additionalWay) {
        LinkedList<Integer> reversed = new LinkedList<>();
        for (Integer num : additionalWay) {
            reversed.offerFirst(num);
        }
        return reversed;
    }

}
