package ru.job4j.srp;

/**
 * CalculatorUI.
 * Start application.
 * @author Shamsemukhametov Rail.
 */

public class CalculatorUI {

    /**
     * User input handler.
     */
    private final IOHandler ioHandler;

    public CalculatorUI() {
        this.ioHandler = new IOHandler(new Calculator());
    }

    /**
     * start.
     * Start main cycle of application.
     */
    public void start() {
        String userAnswer;
        do {
            userAnswer = ioHandler.askOperation();
            if (!userAnswer.equals("exit")) {
                double num1 = ioHandler.handlePrevCalculation();
                double num2 = ioHandler.getNumber("num2");
                double result = ioHandler.handleOperation(userAnswer, num1, num2);
                System.out.println(String.format("result: %s", result));
            }
        } while (!userAnswer.equals("exit"));
    }

    public static void main(String[] args) {
        new CalculatorUI().start();
    }

}
