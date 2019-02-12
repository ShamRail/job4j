package ru.job4j.tdd;


/**
 * Occur when, input string template is incorrect.
 */
public class InvalidStringTemplate extends RuntimeException {
    public InvalidStringTemplate(String message) {
        super(message);
    }
}
