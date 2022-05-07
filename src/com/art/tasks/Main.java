package com.art.tasks;

import com.art.tasks.doubly.DoublyListApp;
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
        return switch (MenuOptions.values()[Integer.parseInt(scanner.nextLine())]) {
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
        };
    }

    private enum MenuOptions {
        EXIT("Exit from program"),
        NODE_STACK("Interact with node stack demo"),
        DOUBLY_LINKED_LIST("Interact with doubly linked list");

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
