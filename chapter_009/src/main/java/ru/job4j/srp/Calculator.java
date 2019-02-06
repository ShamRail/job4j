package ru.job4j.srp;

/**
 * Calculator.
 * Do arithmetic operation with numbers.
 * @author Shamsemukhametov Rail.
 */

public class Calculator {

    /**
     * previous calculation value.
     */
    private Double previousCalculation = null;

    /**
     * sum.
     * @param left left operand.
     * @param right right operand.
     * @return  sum of operands.
     */
    public double sum(double left, double right) {
        previousCalculation = left + right;
        return previousCalculation;
    }

    /**
     * sub.
     * @param left left operand.
     * @param right right operand.
     * @return result of subtraction of operands.
     */
    public double sub(double left, double right) {
        previousCalculation = left - right;
        return previousCalculation;
    }

    /**
     * mult.
     * @param left left operand.
     * @param right right operand.
     * @return result of multiplying operands.
     */
    public double mult(double left, double right) {
        previousCalculation = left * right;
        return previousCalculation;
    }

    /**
     * div.
     * @param left left operand.
     * @param right right operand.
     * @return result of dividing left on right.
     */
    public double div(double left, double right) {
        previousCalculation = left / right;
        return previousCalculation;
    }

    /**
     * getPreviousCalculation.
     * @return value of previous calculation.
     */
    public Double getPreviousCalculation() {
        return this.previousCalculation;
    }
}
