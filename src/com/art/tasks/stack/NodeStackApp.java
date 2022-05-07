package com.art.tasks.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

public class NodeStackApp {
    private final NodeStack<String> nodeStack = new NodeStack<>();
    private final Scanner scanner = new Scanner(System.in);

    public void execute() {
        do {
            System.out.println();
            Arrays.stream(MenuOptions.values()).forEach(System.out::println);
        } while (userInteraction());
    }

    private boolean userInteraction() {
        return switch (MenuOptions.values()[Integer.parseInt(scanner.nextLine())]) {
            case EXIT -> false;
            case PUSH -> {
                System.out.println("Please enter string value you want to add");
                nodeStack.push(scanner.nextLine());
                yield true;
            }
            case POP -> {
                try {
                    System.out.println("Value returned from stack : " + nodeStack.pop());
                } catch (EmptyStackException ignored) {
                    System.out.println("Stack is empty, value can't be retrieved!");
                }
                yield true;
            }
            case SIZE -> {
                System.out.println(nodeStack.size());
                yield true;
            }
            case DISPLAY -> {
                nodeStack.display();
                yield true;
            }
        };
    }

    private enum MenuOptions {
        EXIT("Return to main menu"),
        PUSH("Add new value"),
        POP("Remove value"),
        SIZE("Display stack size"),
        DISPLAY("Display stack contents");

        private final String text;

        MenuOptions(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return this.ordinal() + ". " + text;
        }
    }
}
