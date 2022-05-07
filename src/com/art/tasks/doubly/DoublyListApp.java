package com.art.tasks.doubly;

import java.util.Arrays;
import java.util.Scanner;

public class DoublyListApp {
    private final Scanner scanner = new Scanner(System.in);
    private final DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

    public DoublyListApp() {
    }

    public void execute() {
        boolean run;
        do {
            System.out.println();
            System.out.println("Please select options to interact with doubly linked list :");
            Arrays.stream(MenuOptions.values())
                    .map(value -> value.ordinal() + value.toString())
                    .forEach(System.out::println);
            run = switch (MenuOptions.values()[Integer.parseInt(scanner.nextLine())]) {
                case ADD_BY_INDEX -> {
                    System.out.println("Please enter index and new value (ex: 2 5)");
                    final int[] arr = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                    list.add(arr[0], arr[1]);
                    yield true;
                }
                case ADD_FIRST -> {
                    System.out.println("Please enter value to add in as first:");
                    final int valueForLast = Integer.parseInt(scanner.nextLine());
                    list.addFirst(valueForLast);
                    yield true;
                }
                case ADD_LAST -> {
                    System.out.println("Please enter value to add as last:");
                    final int valueForFirst = Integer.parseInt(scanner.nextLine());
                    list.add(valueForFirst);
                    yield true;
                }
                case DELETE_FIRST -> {
                    list.deleteFirst();
                    System.out.println("You have deleted first element");
                    yield true;
                }
                case DELETE_BY_INDEX -> {
                    System.out.println("Please enter index for the value you want to delete:");
                    list.delete(Integer.parseInt(scanner.nextLine()));
                    yield true;
                }
                case DELETE_LAST -> {
                    list.deleteLast();
                    System.out.println("You have deleted last element");
                    yield true;
                }

                case DISPLAY -> {
                    System.out.println(list);
                    yield true;
                }
                case EXIT -> false;
                case SIZE -> {
                    System.out.println(list.getSize());
                    yield true;
                }
            };
        } while (run);
    }

    private enum MenuOptions {
        EXIT("Return to main menu"),
        ADD_LAST("Add element in the end (append)"),
        ADD_BY_INDEX("Add element by index"),
        ADD_FIRST("Add element in the beginning"),
        DELETE_FIRST("Delete first element"),
        DELETE_BY_INDEX("Delete element by index"),
        DELETE_LAST("Delete last element"),
        DISPLAY("Display all elements"),
        SIZE("Display size of the list");

        private final String text;

        MenuOptions(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return ordinal() + ". " + text;
        }
    }
}
