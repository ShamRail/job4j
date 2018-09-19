package jmm;

public class Read implements Runnable {

    private Counter counter;

    public Read(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        System.out.println(String.format("Read value = %s", counter.getCount()));
    }
}
