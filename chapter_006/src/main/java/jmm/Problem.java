package jmm;

public class Problem {

    public static void main(String[] args) throws InterruptedException {
        Problem problem = new Problem();
        problem.raceCondition();
        problem.visibility();
    }

    private void raceCondition() throws InterruptedException {
        Counter counter = new Counter();
        Thread threadForWritingFirst = new Thread(new Write(counter));
        Thread threadForWritingSecond = new Thread(new Write(counter));
        System.out.println("Race condition");
        threadForWritingFirst.start();
        threadForWritingSecond.start();
        threadForWritingFirst.join();
        threadForWritingSecond.join();
    }


    private void visibility() throws InterruptedException {
        Counter counter = new Counter();
        Thread threadForReading = new Thread(new Read(counter));
        Thread threadForWriting = new Thread(new Write(counter));
        System.out.println("Visibility");
        threadForWriting.start();
        threadForReading.start();
        threadForWriting.join();
        threadForReading.join();
    }
}
