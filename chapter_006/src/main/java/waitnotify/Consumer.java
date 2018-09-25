package waitnotify;

public class Consumer implements Runnable {

    private SimpleBlockingQueue<Integer> simpleBlockingQueue;

    public Consumer(SimpleBlockingQueue<Integer> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            System.out.println(String.format("Произошло взятие %s", this.simpleBlockingQueue.poll()));
        }
    }

}