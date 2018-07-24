package ru.job4j.coffeemachine;

public enum Nominal {
    TEN(10), FIVE(5), TWO(2), ONE(1);

    private int nominal;

    Nominal(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return this.nominal;
    }
}
