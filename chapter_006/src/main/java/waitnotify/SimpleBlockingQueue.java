package waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    @GuardedBy("this")
    private final int maxSize;

    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public synchronized void offer(T value) {
        while (this.queue.size() > 10) {
            try {
                System.out.println("Ожидается взятие");
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.queue.offer(value);
        System.out.println(String.format("Добавлен элемент = %s", value));
        this.notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            System.out.println("Ожидается добавление");
            this.wait();
        }
        T result = queue.poll();
        System.out.println(String.format("Взят элемент =  %s", result));
        this.notify();
        return result;
    }
}
