package com.art.tasks.other;

/**
 * For palindrome and brackets tasks (task 11 and 12)
 */
public class OtherTasks {
    private OtherTasks() {
    }

    /**
     * @param chars char array to check is it palindrome
     * @return true if palindrome or false otherwise
     */
    public static boolean isPalindrome(final char[] chars) {
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) return false;
        }
        return true;
    }

    /**
     * @param string value to check if brackets correctly written in an expression
     * @return true if correct and false otherwise
     */
    public static boolean correctBrackets(final String string) {
        final char open = '(';
        final char close = ')';
        int count = 0;
        for (final char c : string.toCharArray()) {
            if (open == c) count++;
            if (close == c) count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
}
