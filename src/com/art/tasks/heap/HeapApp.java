package com.art.tasks.heap;

import java.util.Arrays;
import java.util.Scanner;

public class HeapApp {
    private final Heap heap = new Heap(50);
    private final Scanner scanner = new Scanner(System.in);

    public void execute() {
        do {
            System.out.println();
            System.out.println("Min heap menu");
            Arrays.stream(MenuOptions.values()).forEach(System.out::println);
        } while (userInteraction());
    }

    private boolean userInteraction() {
        final MenuOptions menuOptions;
        try {
            menuOptions = MenuOptions.values()[Integer.parseInt(scanner.nextLine())];
        } catch (Exception ignored) {
            System.out.println("Invalid option!");
            return true;
        }
        return switch (menuOptions) {
            case EXIT -> false;
            case INSERT -> {
                System.out.println("Enter integer value you want to insert into min heap");
                heap.insert(Integer.parseInt(scanner.nextLine()));
                yield true;
            }
            case REMOVE -> {
                heap.removeRoot();
                yield true;
            }
            case DISPLAY -> {
                System.out.println(heap);
                yield true;
            }
        };
    }

    private enum MenuOptions {
        EXIT("Return to main menu"),
        INSERT("Insert value"),
        REMOVE("Remove root element"),
        DISPLAY("Display heap");
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
