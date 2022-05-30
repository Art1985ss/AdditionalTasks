package com.art.tasks.heap;

public class Heap {
    private final int maxSize;
    private final boolean minHeap;
    private final int[] arr;
    private int size;

    /**
     * Creates min heap by default
     *
     * @param maxSize max number of heap elements
     */
    public Heap(final int maxSize) {
        this(maxSize, true);
    }

    /**
     * Can be created max or min heap
     *
     * @param maxSize max number of heap elements
     * @param minHeap determines if this heap should be min heap or max heap
     */
    public Heap(final int maxSize, final boolean minHeap) {
        this.maxSize = maxSize;
        this.minHeap = minHeap;
        arr = new int[maxSize + 1];
    }

    public void insert(final int value) {
        if (size >= maxSize) return;
        arr[size] = value;
        int index = size++;
        while (minHeap && arr[getParent(index)] > arr[index]) {
            swap(getParent(index), index);
            index = getParent(index);
        }
        while (!minHeap && arr[getParent(index)] < arr[index]) {
            swap(getParent(index), index);
            index = getParent(index);
        }
    }

    private int getParent(final int index) {
        return (index - 1) / 2;
    }

    private void swap(final int index1, final int index2) {
        int value = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = value;
    }

    public void removeRoot() {
        if (size == 0) return;
        arr[0] = arr[--size];
        heapify();
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(arr[i]).append(" ");
        }
        return stringBuilder.toString();
    }

    private void heapify() {
        int index = 0;
        while (minHeap && !isLeaf(index) && arr[index] > arr[getChild(index)]) {
            int tempIndex = getChild(index);
            swap(index, getChild(index));
            index = tempIndex;
        }
        while (!minHeap && !isLeaf(index) && arr[index] < arr[getChild(index)]) {
            int tempIndex = getChild(index);
            swap(index, getChild(index));
            index = tempIndex;
        }
    }

    private int getChild(final int index) {
        final int left = getLeft(index);
        final int right = getRight(index);
        if (left == -1) return right;
        if (right == -1) return left;
        if (minHeap) return arr[left] <= arr[right] ? left : right;
        else return arr[left] >= arr[right] ? left : right;
    }

    private int getLeft(final int index) {
        final int i = 2 * index + 1;
        return i > size ? -1 : i;
    }

    private int getRight(final int index) {
        final int i = 2 * index + 2;
        return i > size ? -1 : i;
    }

    private boolean isLeaf(final int index) {
        return getChild(index) == -1;
    }

    public static void main(String[] args) {
        Heap heap = new Heap(50);
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        heap.insert(50);
        heap.insert(60);
        heap.insert(70);
        heap.insert(80);
        System.out.println(heap);
        heap.removeRoot();
        System.out.println(heap);
        heap.insert(10);
        System.out.println(heap);
        heap.insert(5);
        System.out.println(heap);
        heap.removeRoot();
        System.out.println(heap);
        heap.removeRoot();
        System.out.println(heap);
        heap.removeRoot();
        System.out.println(heap);
    }
}
