package ru.job4j.srp.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Описывает базовые арифметические действия.
 */
public class Calculator {

    /**
     * Операции, заданные по их идентификатору.
     */
    protected Map<OperationIdentifer, Operation<Double>> operations = new HashMap<>();

    /**
     * Значение последнего вычислениея.
     */
    protected Double previousCalculation = null;

    public Calculator() {
        this.operations.put(new OperationIdentifer("+", 2), args -> previousCalculation = args[0] + args[1]);
        this.operations.put(new OperationIdentifer("-", 2), args -> previousCalculation = args[0] - args[1]);
        this.operations.put(new OperationIdentifer("*", 2), args -> previousCalculation = args[0] * args[1]);
        this.operations.put(new OperationIdentifer("/", 2), args -> previousCalculation = args[0] / args[1]);
    }

    /**
     * Вычисляет значение функции для заданных аргументов.
     * @param operationName имя функции.
     * @param args аргументы функции.
     * @return значение.
     */
    public Double calculate(String operationName, Double... args) {
         return getEntryByName(operationName)
                 .map(operationEntry -> operationEntry.getValue().apply(args))
                 .orElse(null);
    }

    public Double getPreviousCalculation() {
        return this.previousCalculation;
    }

    /**
     * Проверяет на пустоту последний результат вычисления.
     * @return
     */
    public boolean previousOperationAvailable() {
        return this.previousCalculation != null;
    }

    /**
     * Возвращет кол-во аргументов функции по ее имени.
     * @param operationName имя функции.
     * @return кол-во аргументов или -1, если функции нет среди доступных операций.
     */
    public int getOperationArgsCount(String operationName) {
        return getEntryByName(operationName)
                .map(operationEntry -> operationEntry.getKey().getArgsCount())
                .orElse(-1);
    }

    /**
     * Находит элемент таблицы фукнций по ее имени.
     * @param operationName имя функции.
     * @return элемент таблицы.
     */
    private Optional<Map.Entry<OperationIdentifer, Operation<Double>>> getEntryByName(String operationName) {
        return this.operations
                .entrySet()
                .stream()
                .filter((operationEntry -> operationEntry.getKey().getOperation().equals(operationName)))
                .findFirst();
    }
}
