package ru.job4j.srp.calculator;

import java.util.Objects;

/**
 * Описывает функцию.
 * В частности ее имя и количество параметров.
 */

public class OperationIdentifer {

    /**
     * Имя функции.
     */
    private final String operation;

    /**
     * Кол-во аргументов.
     */
    private final int argsCount;

    public OperationIdentifer(String operation, int argsCount) {
        this.operation = operation;
        this.argsCount = argsCount;
    }

    public int getArgsCount() {
        return this.argsCount;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        }
        if (o == null || getClass() != o.getClass()) {
            result = false;
        }
        OperationIdentifer that = (OperationIdentifer) o;
        result = argsCount == that.argsCount && Objects.equals(operation, that.operation);
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result += 31 * operation.hashCode();
        result += 31 * argsCount;
        return result;
    }
}
