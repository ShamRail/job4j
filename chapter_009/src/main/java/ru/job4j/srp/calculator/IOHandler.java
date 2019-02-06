package ru.job4j.srp.calculator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Обрабатывает колсольные запросы пользователя.
 */
public class IOHandler {

    private final Scanner scanner = new Scanner(System.in);

    private final Calculator calculator;

    public IOHandler(Calculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Спрашивает операцию.
     * @return имя операции.
     */
    public String askOperation() {
        System.out.print("operation: ");
        return this.scanner.next();
    }

    /**
     * Определяет нужно использовать предыдещщее значение или новое.
     * @return предыщий результат вычисление или новое значение.
     */
    public boolean usePreviousOperation() {
        boolean result = calculator.previousOperationAvailable();
        if (result) {
            System.out.print("use previous calculation: ");
            String answer = scanner.next();
            result = answer.equals("yes");
        }
        return result;
    }

    /**
     * Спрашивает у пользователя аргументы функции.
     * @param operationName имя функции.
     * @param flag флаг, показывающий нужно ли использовать прошлый результат.
     * @return аргументы функции.
     */
    public Double[] getInputValues(String operationName, boolean flag) {
        ArrayList<Double> values = new ArrayList<>(10);
        int argsCount = calculator.getOperationArgsCount(operationName);
        int start = 0;
        if (flag) {
            System.out.println(String.format("num1: %s", calculator.getPreviousCalculation()));
            values.add(calculator.getPreviousCalculation());
            start = 1;
        }
        for (int i = start; i < argsCount; i++) {
            System.out.print(String.format("num%s: ", i + 1));
            values.add(scanner.nextDouble());
        }
        Double[] result = new Double[values.size()];
        return  values.toArray(result);
    }

    /**
     * Выводит результат вычеслений.
     * @param result результат.
     */
    public void showResult(double result) {
        System.out.println(String.format("result: %s", result));
    }
}
