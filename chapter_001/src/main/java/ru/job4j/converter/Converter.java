package ru.job4j.converter;


/**
 * Конвертер валюты.
 * @version 1.0
 * @since 05.07.2018
 * @author Rail Shamsemukhametov
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        return value/70;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллары
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }
    /**
     * Конвертируем евро в рубли.
     * @param value евро
     * @return Рубли
     * */

    public  int euroToRuble(int value){
        return value * 70 ;
    }

    /**
     * Конвертируем доллары в рубли
     * @param value доллары
     * @return Рубли
     * */

    public  int dollarToRuble(int value){
        return  value * 60 ;
    }
}
