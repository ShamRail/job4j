package additionalquestions;

public class State {

    /**
     * current number of executing thread.
     * */

    private int number;

    /**
     * thread count.
     * */

    private final int threadCount;

    public State(int number, int threadCount) {
        this.number = number;
        this.threadCount = threadCount;
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * changeState.
     * increase number.
     * */

    public void changeState() {
        number = (++number) % threadCount;
    }

}
