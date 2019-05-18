package graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/***
 * Вершина-узел графа.
 */

public class Node {

    /**
     * Номер вершины.
     * */

    private int number;

    /**
     * Соседние вершины.
     * */

    private List<Node> nodes;

    public Node(int number) {
        this.number = number;
        this.nodes = new LinkedList<>();
    }

    /***
     * Добавление смежной вершины.
     * @param node смежная вершина.
     */

    public void join(Node node) {
        if (!node.equals(node)) {
            this.nodes.add(node);
        }
    }

    public int getNumber() {
        return this.number;
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return number == node.number && Objects.equals(nodes, node.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, nodes);
    }
}
