package ru.job4j.strategy;

public class Square implements Shape {
    int side;

    public Square(int side) {
        this.side = side;
    }

    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder firstrow = new StringBuilder();
        for (int i = 0; i < side; i++) {
            firstrow.append('*');
        }
        for (int i = 0; i < side; i++) {
            stringBuilder.append(firstrow.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
