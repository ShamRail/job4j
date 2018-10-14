package threadpools;

import waitnotify.SimpleBlockingQueue;

public class ExecutingThread implements Runnable {

    private final SimpleBlockingQueue<Runnable> simpleBlockingQueue;

    public ExecutingThread(SimpleBlockingQueue<Runnable> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public synchronized void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable task = simpleBlockingQueue.poll();
                task.run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
