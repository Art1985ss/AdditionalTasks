package com.art.tasks.stack;

import java.util.EmptyStackException;

public interface Stack<T> {
    void push(T value);

    T pop() throws EmptyStackException;

    void display();

    int size();
}
