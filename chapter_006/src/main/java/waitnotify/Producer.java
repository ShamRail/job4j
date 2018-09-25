package waitnotify;

public class Producer implements Runnable {

    private SimpleBlockingQueue<Integer> simpleBlockingQueue;

    public Producer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            this.simpleBlockingQueue.offer(i + 1);
            System.out.println(String.format("Произошло добавление %s", i + 1));
        }
    }
}
