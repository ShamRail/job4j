package ru.job4j.srp;

public class Calculator {

    private Double previousCalculation = null;

    public double sum(double left, double right) {
        previousCalculation = left + right;
        return previousCalculation;
    }

    public double sub(double left, double right) {
        previousCalculation = left - right;
        return previousCalculation;
    }

    public double mult(double left, double right) {
        previousCalculation = left * right;
        return previousCalculation;
    }

    public double div(double left, double right) {
        previousCalculation = left / right;
        return previousCalculation;
    }

    public Double getPreviousCalculation() {
        return this.previousCalculation;
    }
}
