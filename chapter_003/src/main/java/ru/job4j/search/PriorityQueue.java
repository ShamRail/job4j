package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        for (int index = 0; index < this.tasks.size(); index++) {
            if (task.getPriority() <= tasks.get(index).getPriority()) {
                this.tasks.add(index, task);
                break;
            }
        }
        if (this.tasks.size() == 0 || task.getPriority() > this.tasks.get(0).getPriority()) {
            this.tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}