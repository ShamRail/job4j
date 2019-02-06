package ru.job4j.srp.calculator;

/**
 * Запускает приложение.
 */
public class CalculatorUI {

    private final Calculator calculator;

    private final IOHandler ioHandler;

    public CalculatorUI(Calculator calculator) {
        this.calculator = calculator;
        this.ioHandler = new IOHandler(calculator);
    }

    /**
     * Запускает главный цикл программы.
     */
    public void start() {
        boolean toUsePreviousResult;
        String operation = ioHandler.askOperation();
        while (!operation.equals("exit")) {
            toUsePreviousResult = ioHandler.usePreviousOperation();
            Double[] args = ioHandler.getInputValues(operation, toUsePreviousResult);
            Double result = calculator.calculate(operation, args);
            ioHandler.showResult(result);
            operation = ioHandler.askOperation();
        }
    }

    public static void main(String[] args) {
        new CalculatorUI(new Calculator()).start();
    }
}
