package ru.job4j.srp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

public class IOHandler {

    private final Calculator calculator;

    private Map<String, BiFunction<Double, Double, Double>> calculateMap = new HashMap<>();

    private Scanner scanner = new Scanner(System.in);

    public IOHandler(final Calculator calculator) {
        this.calculator = calculator;
        this.putOperations();
    }

    private void putOperations() {
        this.calculateMap.put("sum", calculator::sum);
        this.calculateMap.put("sub", calculator::sub);
        this.calculateMap.put("mult", calculator::mult);
        this.calculateMap.put("div", calculator::div);
    }

    public Double handleOperation(String operation, double left, double right) {
        return calculateMap.get(operation).apply(left, right);
    }

    public double getNumber(String varName) {
        System.out.print(String.format("%s: ", varName));
        return scanner.nextDouble();
    }

    public double handlePrevCalculation() {
        double result;
        if (calculator.getPreviousCalculation() != null) {
            System.out.print("use previous calculation: ");
            String usePrevCalcalution = scanner.next();
            result = calculator.getPreviousCalculation();
            if (usePrevCalcalution.equals("yes")) {
                System.out.println(String.format("num1: %s", result));
            } else {
                System.out.print("num1: ");
                result = scanner.nextDouble();
            }
        } else {
            result = this.getNumber("num1");
        }
        return result;
    }

    public String askOperation() {
        System.out.print("operation: ");
        return scanner.next();
    }
}
