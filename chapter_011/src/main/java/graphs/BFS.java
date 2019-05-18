package graphs;

import java.util.*;

/**
 * Поиск в ширину.
 * */

public class BFS {

    /***
     * Находит путь от начала до конца, используя обход в ширину.
     * @param start начальная вершина.
     * @param end конечная вершина.
     * @return путь.
     */

    public List<Node> search(Node start, Node end) {
        List<Node> way = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);
        Node node;
        do {
            node = queue.poll();
            if (!visited.contains(node)) {
                for (Node neighbour : node.getNodes()) {
                    queue.offer(neighbour);
                }
                visited.add(node);
                way.add(node);
            }
        } while (!queue.isEmpty() && !node.equals(end));
        return way;
    }

}
