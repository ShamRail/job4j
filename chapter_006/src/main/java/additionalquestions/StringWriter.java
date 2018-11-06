package additionalquestions;

public class StringWriter implements Runnable {

    private final StringPresentation string;

    private final int number;

    private final int orderNumber;

    private State state;

    public StringWriter(StringPresentation string, int number, int orderNumber, State state) {
        this.string = string;
        this.number = number;
        this.orderNumber = orderNumber;
        this.state = state;
    }

    @Override
    public void run() {
        String s = null;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (string) {
                    while (state.getNumber() != orderNumber) {
                        string.wait();
                    }
                    for (int i = 0; i < 10; i++) {
                        s = string.appendNumber(number);
                    }
                    System.out.println(s);
                    state.changeState();
                    string.notify();
                    string.wait();
                }
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
