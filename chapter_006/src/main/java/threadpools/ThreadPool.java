package threadpools;

import waitnotify.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final List<Thread> threads;
    private final SimpleBlockingQueue<Runnable> tasks;
    private volatile boolean isThreadsStopped = false;

    public ThreadPool(int maxTasksCount) {
        this.threads = new LinkedList<>();
        this.tasks = new SimpleBlockingQueue<>(maxTasksCount);
        int coresCount = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < coresCount; i++) {
            threads.add(new Thread(new ExecutingThread(this.tasks)));
        }
        for (Thread thread : this.threads) {
            thread.start();
        }
    }

    public void work(Runnable job) {
        if (isThreadsStopped) {
            throw new IllegalStateException("Threads are already stopped!");
        }
        tasks.offer(job);
    }

    public void shutdown() {
        isThreadsStopped = true;
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
