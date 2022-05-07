package com.art.tasks.binary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BinaryTree<T extends Comparable<T>> implements Traversable, BinaryTreeOperations<T> {
    private Node<T> root;

    public BinaryTree() {
    }

    public BinaryTree(final T value) {
        this.root = new Node<>(value);
    }


    @Override
    public void add(final T value) {
        root = addRecursive(root, value);
    }

    @Override
    public void delete(final T value) {
        deleteRecursive(root, value);
    }

    @Override
    public boolean search(final T value) {
        return searchRecursive(root, value);
    }

    @Override
    public String preOder() {
        final List<T> values = new ArrayList<>();
        recursivePreOrder(values, root);
        return "[" + values.stream().map(Objects::toString).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public String inOrder() {
        final List<T> values = new ArrayList<>();
        recursiveInOrder(values, root);
        return "[" + values.stream().map(Objects::toString).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public String postOrder() {
        final List<T> values = new ArrayList<>();
        recursivePostOrder(values, root);
        return "[" + values.stream().map(Objects::toString).collect(Collectors.joining(", ")) + "]";
    }

    private Node<T> addRecursive(final Node<T> current, final T value) {
        if (current == null) return new Node<>(value);

        if (current.value.compareTo(value) > 0) current.left = addRecursive(current.left, value);
        else if (current.value.compareTo(value) < 0) current.right = addRecursive(current.right, value);
        else return current;
        return current;
    }

    private boolean searchRecursive(final Node<T> current, final T value) {
        if (current == null) return false;
        if (current.value.equals(value)) return true;

        if (current.value.compareTo(value) > 0) return searchRecursive(current.left, value);
        else return searchRecursive(current.right, value);
    }

    private Node<T> deleteRecursive(final Node<T> current, final T value) {
        if (current == null) return null;
        if (current.value.equals(value)) {
            if (current.isLeaf()) return null;
            if (current.left == null) return current.right;
            if (current.right == null) return current.left;
            final T smallestValue = smallestValue(current.right, value);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        }

        if (current.value.compareTo(value) > 0) current.left = deleteRecursive(current.left, value);
        else current.right = deleteRecursive(current.right, value);
        return current;
    }

    private T smallestValue(final Node<T> parent, final T value) {
        return parent.left == null ? parent.value : smallestValue(parent.left, value);
    }

    private void recursivePreOrder(final List<T> list, final Node<T> node) {
        if (node == null) return;
        list.add(node.value);
        recursivePreOrder(list, node.left);
        recursivePreOrder(list, node.right);
    }

    private void recursiveInOrder(final List<T> list, final Node<T> node) {
        if (node == null) return;
        recursiveInOrder(list, node.left);
        list.add(node.value);
        recursiveInOrder(list, node.right);
    }

    private void recursivePostOrder(final List<T> list, final Node<T> node) {
        if (node == null) return;
        recursivePostOrder(list, node.left);
        recursivePostOrder(list, node.right);
        list.add(node.value);
    }

    private class Node<S> {

        private S value;
        private Node<S> left;
        private Node<S> right;

        public Node(final S value) {
            this.value = value;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
