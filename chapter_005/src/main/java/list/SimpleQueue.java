package list;

import generetics.SimpleStack;

public class SimpleQueue<E> {
    private SimpleStack<E> inputStack = new SimpleStack<>();
    private SimpleStack<E> outputStack = new SimpleStack<>();

    public void push(E item) {
        inputStack.push(item);
    }

    public E poll() {
        if (outputStack.isEmpty()) {
            while (!inputStack.isEmpty()) {
                outputStack.push(inputStack.poll());
            }
        }
        return (outputStack.isEmpty()) ? null : outputStack.poll();
    }
}
