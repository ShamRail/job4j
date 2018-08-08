package ru.job4j.lambda;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Functions.
 * Calculate values of math functions in range.
 * @since 08.08.2018.
 * @author Rail Shamsemukhametov.
 * */

public class Functions {

    /**
     * diaposon.
     * Calculate values according to input function.
     * @param start beginning of the range.
     * @param end end of the range.
     * @return list of values.
     * */

    private List<Double> diapason(int start, int end, Function<Integer, Double> func) {
        List<Double> result = new LinkedList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply(i));
        }
        return result;
    }

    /**
     * linearFunction.
     * Describe linear math function.
     * @param start beginning of the range.
     * @param end end of the range.
     * @return list of values.
     * */

    public List<Double> linearFunction(int start, int end) {
        return this.diapason(start, end, Double::new);
    }

    /**
     * logarithmFunction.
     * Describe logarithm math function.
     * @param start beginning of the range.
     * @param end end of the range.
     * @return list of values.
     * */

    public List<Double> logarithmFunction(int start, int end) {
        return this.diapason(start, end, integer -> Math.log(new Double(integer)));
    }

    /**
     * squareFunction.
     * Describe square math function.
     * @param start beginning of the range.
     * @param end end of the range.
     * @return list of values.
     * */

    public List<Double> squareFunction(int start, int end) {
        return this.diapason(start, end, integer -> Math.pow(new Double(integer), 2));
    }
}
