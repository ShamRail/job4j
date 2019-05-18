package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/***
 * Поиск в глубину.
 */

public class DFS {

    /***
     * Находит путь от начала до конца, используя обход в глубину.
     * @param start начальная вершина.
     * @param end конечная вершина.
     * @return путь.
     */

    public List<Node> search(Node start, Node end) {
        List<Node> way = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        LinkedList<Node> stack = new LinkedList<>();
        stack.offerFirst(start);
        Node node;
        do {
            node = stack.pollFirst();
            if (!visited.contains(node)) {
                for (Node neighbour : node.getNodes()) {
                    stack.offerFirst(neighbour);
                }
                way.add(node);
                visited.add(node);
            }
        } while (!stack.isEmpty() && !node.equals(end));
        return way;
    }

}
