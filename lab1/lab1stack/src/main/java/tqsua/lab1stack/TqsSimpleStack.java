package tqsua.lab1stack;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class TqsSimpleStack<T> {
    private ArrayList<T> s = new ArrayList<>();
    private int maxSize;
    private boolean bounded = false;

    public TqsSimpleStack() {}

    public TqsSimpleStack(int x) {
        this.maxSize = x;
        this.bounded = true;
    }

    public void push(T x) {
        if (bounded) {
            if (this.s.size()<this.maxSize) {
                this.s.add(x);
            } else {
                throw new IllegalStateException();
            }
        } else {
            this.s.add(x);
        }
    }

    public T pop() {
        if (this.s.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.s.remove(this.s.size() - 1);
        }
    }

    public T peek() {
        if (this.s.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            return this.s.get(this.s.size() - 1);
        }
    }

    public int size() {
        return this.s.size();
    }

    public boolean isEmpty() {
        return this.s.isEmpty();
    }

}