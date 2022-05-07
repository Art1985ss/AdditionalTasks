package com.art.tasks.binary;

public interface BinaryTreeOperations<T> {
    void add(final T value);
    void delete(T value);
    boolean search(final T value);
}
