package ru.job4j.pinpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    private final int leftBorder = 0;
    private final int rightBorder = 290;
    private final int sleep = 50;
    private final int shiftLeft = -1;
    private final int shiftRight = 1;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            int shift = 1;

            if (this.rect.getX() == rightBorder) {
                shift = shiftLeft;
            }

            if (this.rect.getX() == leftBorder) {
                shift = shiftRight;
            }

            this.rect.setX(this.rect.getX() + shift);

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}