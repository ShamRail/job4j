package ru.job4j.srp.calculator;

/**
 * Инжерный калькулятор.
 * Расширяет простой арифметический калькулятор.
 */
public class EngineerCalculator extends Calculator {

    public EngineerCalculator() {
        super.operations.put(new OperationIdentifer("sin", 1), (args) -> previousCalculation = Math.sin(args[0]));
        super.operations.put(new OperationIdentifer("cos", 1), (args) -> previousCalculation = Math.cos(args[0]));
        super.operations.put(new OperationIdentifer("tan", 1), (args) -> previousCalculation = Math.tan(args[0]));
        super.operations.put(new OperationIdentifer("ln", 1), (args) ->  previousCalculation = Math.log(args[0]));
        super.operations.put(new OperationIdentifer("exp", 1), (args) -> previousCalculation = Math.exp(args[0]));
    }

}
