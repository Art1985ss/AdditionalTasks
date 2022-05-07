package com.art.tasks.postfix;

import java.util.Arrays;
import java.util.Scanner;

public class PostfixCalculatorApp {
    private final Scanner scanner = new Scanner(System.in);
    private final PostfixCalculator postfixCalculator = new PostfixCalculator();

    public void execute() {
        do {
            Arrays.stream(MenuOptions.values()).forEach(System.out::println);
        } while (userInteraction());
    }

    private boolean userInteraction() {
        return switch (MenuOptions.values()[Integer.parseInt(scanner.nextLine())]) {
            case EXIT -> false;
            case INFIX_TO_POSTFIX -> {
                System.out.println("Please enter your expression you want to convert for example: ( 7 + 10 - 2 ) * 3 + 8 / 4");
                System.out.println("There should be spaces between elements");
                final PostfixCalculator.Result result = postfixCalculator.infixToPostfix(scanner.nextLine());
                System.out.println(result.consoleOutput());
                yield true;
            }
            case CALCULATE_POSTFIX_LIFO -> {
                System.out.println("Please enter your expression you want to convert for example: ( 7 + 10 - 2 ) * 3 + 8 / 4");
                System.out.println("There should be spaces between elements");
                final PostfixCalculator.Result result = postfixCalculator.calculatePostfix(scanner.nextLine(), true);
                System.out.println(result.consoleOutput());
                yield true;
            }
            case CALCULATE_POSTFIX_POLISH -> {
                System.out.println("Please enter your expression you want to convert for example: ( 7 + 10 - 2 ) * 3 + 8 / 4");
                System.out.println("There should be spaces between elements");
                final PostfixCalculator.Result result = postfixCalculator.calculatePostfix(scanner.nextLine(), false);
                System.out.println(result.consoleOutput());
                yield true;
            }
        };
    }


    private enum MenuOptions {
        EXIT("Return to main menu"),
        INFIX_TO_POSTFIX("Convert infix to postfix"),
        CALCULATE_POSTFIX_LIFO("Calculate postfix using lifo principle (from lectures)"),
        CALCULATE_POSTFIX_POLISH("Calculate postfix using polish notation");
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
