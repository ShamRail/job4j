package additionalquestions;

public class Switcher {

    private Thread first;

    private Thread second;

    private volatile State state = new State(0, 2);

    private StringPresentation stringPresentation = new StringPresentation();

    public Switcher() {
        this.first = new Thread(new StringWriter(stringPresentation, 1, 0, state));
        this.second = new Thread(new StringWriter(stringPresentation, 2, 1, state));
    }

    public void start() {
        first.start();
        second.start();
    }

    public void stop() {
        first.interrupt();
        second.interrupt();
    }
}
