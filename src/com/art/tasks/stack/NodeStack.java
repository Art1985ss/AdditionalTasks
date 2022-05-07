package com.art.tasks.stack;

import org.w3c.dom.Node;

import java.util.EmptyStackException;

public class NodeStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;

    @Override
    public void push(final T value) {
        final Node<T> node = new Node<>(value);
        node.nextNode = top;
        top = node;
        size++;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (top == null) throw new EmptyStackException();
        final Node<T> result = top;
        top = top.nextNode;
        size--;
        return result.value;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        final StringBuilder sb = new StringBuilder("[");
        Node<T> node = top;
        while (node != null) {
            sb.append(node.value).append(node.nextNode == null ? "]" : ", ");
            node = node.nextNode;
        }
        return sb.toString();
    }

    private class Node<T> {
        private final T value;
        private Node<T> nextNode;

        public Node(final T value) {
            this.value = value;
        }
    }
}
