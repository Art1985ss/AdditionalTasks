package com.art.tasks.doubly;

public class DoublyLinkedList<T> {
    private Node<T> headNode;
    private Node<T> tailNode;
    private int size = 0;

    public DoublyLinkedList() {
    }

    public DoublyLinkedList(final T value) {
        add(value);
    }

    public void add(final T value) {
        final Node<T> node = new Node<>(value);
        if (headNode == null) {
            headNode = node;
        } else {
            if (headNode == tailNode) headNode.nextNode = node;
            this.tailNode.nextNode = node;
            node.prevNode = tailNode;
        }
        tailNode = node;
        this.size++;
    }

    public void add(final int index, final T value) {
        if (size == 0 || index > size - 1) {
            add(value);
            return;
        }
        final Node<T> node = new Node<>(value);
        int i = 0;
        Node<T> iterNode = headNode;
        while (true) {
            if (i == index) {
                final Node<T> prevNode = iterNode.prevNode;
                prevNode.nextNode = node;
                iterNode.prevNode = node;
                node.prevNode = prevNode;
                node.nextNode = iterNode;
                this.size++;
                return;
            }
            if (iterNode.nextNode == null) {
                node.prevNode = tailNode;
                iterNode.nextNode = node;
                this.tailNode = node;
                this.size++;
                return;
            } else {
                iterNode = iterNode.nextNode;
            }
            i++;
        }
    }

    public void addFirst(final T value) {
        Node<T> node = new Node<>(value);
        headNode.prevNode = node;
        node.nextNode = headNode;
        headNode = node;
        this.size++;
    }

    public void deleteLast() {
        if (size == 1) {
            resetToEmpty();
        } else {
            tailNode = tailNode.prevNode;
            tailNode.nextNode = null;
            size--;
        }
    }

    public void deleteFirst() {
        if (size == 1) {
            resetToEmpty();
        } else {
            headNode = headNode.nextNode;
            headNode.prevNode = null;
            size--;
        }
    }

    public void delete(final int index) {
        if (size == 1) {
            resetToEmpty();
            return;
        }
        int i = 0;
        Node<T> iterNode = headNode;
        while (iterNode != null) {
            if (i == index) {
                final Node<T> prevNode = iterNode.prevNode;
                final Node<T> nextNode = iterNode.nextNode;
                prevNode.nextNode = nextNode;
                nextNode.prevNode = prevNode;
                size--;
                return;
            }
            iterNode = iterNode.nextNode;
            i++;
        }
    }

    public int getSize() {
        return this.size;
    }

    private void resetToEmpty() {
        this.headNode = null;
        this.tailNode = null;
        size = 0;
    }

    private class Node<T> {
        private final T value;
        private Node<T> nextNode;
        private Node<T> prevNode;

        public Node(final T value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";
        final StringBuilder sb = new StringBuilder("[");
        Node<T> iterNode = headNode;
        while (iterNode != null) {
            sb.append(iterNode.value).append(iterNode.nextNode == null ? "]" : ", ");
            iterNode = iterNode.nextNode;
        }
        return sb.toString();
    }
}
