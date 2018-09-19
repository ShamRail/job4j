package jmm;

public class Write implements Runnable {

    private Counter counter;

    public Write(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            counter.increase();
        }
        System.out.println(String.format("Written value = %s", counter.getCount()));
    }
}
