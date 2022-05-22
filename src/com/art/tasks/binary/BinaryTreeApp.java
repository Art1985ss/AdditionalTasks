package com.art.tasks.binary;

import java.util.Arrays;
import java.util.Scanner;

public class BinaryTreeApp {
    private final Scanner scanner = new Scanner(System.in);
    private final BinaryTree<Integer> binaryTree = new BinaryTree<>();

    public void execute() {
        do {
            System.out.println();
            System.out.println("This menu includes 2 tasks, 4(binary search tree) and 6 (traversal)");
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
            case ADD -> {
                System.out.println("Please enter integer value to be added");
                binaryTree.add(Integer.parseInt(scanner.nextLine()));
                yield true;
            }
            case CONTAINS -> {
                System.out.println("Please enter integer value you want to check if it present in the tree");
                boolean present = binaryTree.search(Integer.parseInt(scanner.nextLine()));
                System.out.println("This value is " + (present ? "" : "not") + " present in the tree");
                yield true;
            }
            case DELETE -> {
                System.out.println("Please enter integer value you want to delete from the tree");
                binaryTree.delete(Integer.parseInt(scanner.nextLine()));
                yield true;
            }
            case INORDER -> {
                System.out.println(binaryTree.inOrder());
                yield true;
            }
            case PREORDER -> {
                System.out.println(binaryTree.preOder());
                yield true;
            }
            case POSTORDER -> {
                System.out.println(binaryTree.postOrder());
                yield true;
            }
        };
    }

    private enum MenuOptions {
        EXIT("Return to main menu"),
        ADD("Add element to the tree"),
        CONTAINS("Check if element is present in the tree"),
        DELETE("Delete element from the tree"),
        INORDER("Display all elements using INORDER sequence"),
        PREORDER("Display all elements using PREORDER sequence"),
        POSTORDER("Display all elements using POSTORDER sequence");
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
