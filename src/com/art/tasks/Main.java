package com.art.tasks;

import com.art.tasks.binary.BinaryTreeApp;
import com.art.tasks.doubly.DoublyListApp;
import com.art.tasks.heap.HeapApp;
import com.art.tasks.postfix.PostfixCalculatorApp;
import com.art.tasks.stack.NodeStackApp;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println();
            Arrays.stream(MenuOptions.values()).forEach(System.out::println);
        } while (userInteraction());
    }

    private static boolean userInteraction() {
        final MenuOptions menuOptions;
        try {
            menuOptions = MenuOptions.values()[Integer.parseInt(scanner.nextLine())];
        } catch (Exception ignored) {
            System.out.println("Invalid option!");
            return true;
        }
        return switch (menuOptions) {
            case EXIT -> {
                System.out.println("Bye bye!");
                yield false;
            }
            case NODE_STACK -> {
                new NodeStackApp().execute();
                yield true;
            }
            case DOUBLY_LINKED_LIST -> {
                new DoublyListApp().execute();
                yield true;
            }
            case HEAP -> {
                new HeapApp().execute();
                yield true;
            }
            case BINARY_TREE -> {
                new BinaryTreeApp().execute();
                yield true;
            }
            case INFIX_POSTFIX -> {
                new PostfixCalculatorApp().execute();
                yield true;
            }
        };
    }

    private enum MenuOptions {
        EXIT("Exit from program"),
        NODE_STACK("Interact with node stack demo (task 1)"),
        DOUBLY_LINKED_LIST("Interact with doubly linked list (task 3)"),
        HEAP("Interact with min heap (task 5)"),
        BINARY_TREE("Binary search tree and traversing a binary tree demo (tasks 4 and 6)"),
        INFIX_POSTFIX("Conversion from infix to postfix and postfix calculations demo (task 9 and 10)");

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
