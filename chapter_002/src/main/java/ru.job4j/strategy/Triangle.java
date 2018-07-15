package ru.job4j.strategy;

public class Triangle implements Shape {

    int height;

    public Triangle(int height) {
        this.height = height;
    }

    public String draw() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < height; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < i + 1; j++) {
                row.append('*');
            }
            stringBuilder.append(row);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
