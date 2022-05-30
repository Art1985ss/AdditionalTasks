package com.art.tasks.other;

import java.util.Arrays;
import java.util.Scanner;

import static com.art.tasks.other.OtherTasks.correctBrackets;
import static com.art.tasks.other.OtherTasks.isPalindrome;

public class OtherTasksApp {
    private final Scanner scanner = new Scanner(System.in);

    public void execute() {
        do {
            System.out.println();
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
            case PALINDROME -> {
                System.out.println("Please enter string : ");
                System.out.println((isPalindrome(scanner.nextLine().toCharArray()) ? "" : "not ") + "palindrome");
                yield true;
            }
            case BRACKETS -> {
                System.out.println("Please enter expression : ");
                System.out.println((correctBrackets(scanner.nextLine()) ? "" : "not ") + "correct");
                yield true;
            }
        };
    }

    private enum MenuOptions {
        EXIT("Return to main menu"),
        PALINDROME("Checks whether a given string is a palindrome"),
        BRACKETS("Checks if brackets are correctly written in an expression");

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
