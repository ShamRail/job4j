package additionalquestions;

public class StringWriter implements Runnable {

    private final StringPresentation string;

    private final int number;

    public StringWriter(StringPresentation string, int number) {
        this.string = string;
        this.number = number;
    }

    @Override
    public void run() {
        String s = null;
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (string) {
                for (int i = 0; i < 10; i++) {
                    s = string.appendNumber(number);
                }
                System.out.println(s);
                string.notify();
                try {
                    string.wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }
}
