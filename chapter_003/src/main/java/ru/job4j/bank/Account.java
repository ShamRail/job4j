package ru.job4j.bank;

/**
 * Account.
 * Класс счет.
 * @version 1.0.
 * @author Rail Shamsemukhametov.
 * @since 24.07.2018.
 * */

public class Account {
    /**Сумма на счете.*/
    private double value;
    /**Реквизиты счета.*/
    private String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return this.requisites;
    }
}
