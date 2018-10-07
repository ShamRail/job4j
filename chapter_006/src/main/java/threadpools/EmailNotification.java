package threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("subject = Notification {%s} to email {%s}", user.getName(), user.getEmail());
            String body = String.format("Add a new event to {%s}", user.getName());
            send(subject, body, user.getEmail());
        });
    }

    public void close() throws InterruptedException {
        this.pool.shutdown();
        while (!pool.isTerminated()) {
            Thread.sleep(100);
        }
    }

    public void send(String suject, String body, String email) {

    }
}
