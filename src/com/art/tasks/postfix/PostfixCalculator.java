package com.art.tasks.postfix;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PostfixCalculator {
    protected static final String LINE_DECORATOR = "==================================\n";
    private final Function<Stack<String>, String> dequeStringFunction = deque -> String.join(" ", deque);
    private final Function<Stack<BigDecimal>, String> dequeBigDecimalFunction = deque -> deque.stream().map(BigDecimal::toString).collect(Collectors.joining(" "));

    public Result infixToPostfix(final String expression) {
        final Stack<String> operationsStack = new Stack<>();
        final Stack<String> output = new Stack<>();
        final StringBuilder sb = new StringBuilder("Convert infix to postfix : ").append(expression).append("\n").append(LINE_DECORATOR);
        Arrays.stream(expression.split("\\s"))
                .forEach(element -> {
                    sb.append("Element : ").append(element).append("\n");
                    final int symbolWeight = getWeight(element);
                    switch (symbolWeight) {
                        case -1:
                            output.push(element);
                            break;
                        case 0:
                            operationsStack.push(element);
                            break;
                        case 1, 2, 3, 4:
                            if (!operationsStack.isEmpty() && getWeight(operationsStack.peek()) >= symbolWeight) {
                                output.push(operationsStack.pop());
                                operationsStack.push(element);
                            } else operationsStack.push(element);
                            break;
                        case 5:
                            boolean notOpeningBracket;
                            do {
                                String symbol = operationsStack.pop();
                                notOpeningBracket = getWeight(symbol) != 0;
                                if (notOpeningBracket) output.push(symbol);
                            } while (notOpeningBracket);
                            break;
                        default:
                            System.out.println("Unsupported symbol " + element);
                    }
                    sb.append("Stack : ").append(dequeStringFunction.apply(operationsStack)).append("\n")
                            .append("Output : ").append(dequeStringFunction.apply(output)).append("\n")
                            .append(LINE_DECORATOR);
                });
        while (!operationsStack.isEmpty()) {
            output.push(operationsStack.pop());
        }
        sb.append("Result : ");
        final String resultExpression = dequeStringFunction.apply(output);
        sb.append(resultExpression);
        return new Result(sb.append("\n\n\n").toString(), resultExpression);
    }

    public Result calculatePostfix(final String expression, final boolean lifo) {
        final StringBuilder sb = new StringBuilder("Calculate postfix : ").append(expression).append("\n").append(LINE_DECORATOR);
        final Stack<BigDecimal> stack = new Stack<>();
        Arrays.stream(expression.split("\\s"))
                .forEach(element -> {
                    sb.append("Element : ").append(element).append("\n");
                    final int symbolWeight = getWeight(element);
                    if (symbolWeight == -1) stack.push(new BigDecimal(element));
                    else {
                        final BigDecimal firstValue;
                        final BigDecimal secondValue;
                        if (lifo) {
                            secondValue = stack.pop();
                            firstValue = stack.pop();
                        } else {
                            firstValue = stack.pop();
                            secondValue = stack.pop();
                        }
                        sb.append(secondValue).append(" ").append(element).append(" ").append(firstValue).append("\n");
                        switch (element) {
                            case "+" -> stack.push(secondValue.add(firstValue));
                            case "-" -> stack.push(secondValue.subtract(firstValue));
                            case "*" -> stack.push(secondValue.multiply(firstValue));
                            case "/" -> stack.push(secondValue.divide(firstValue, 5, RoundingMode.HALF_EVEN));
                            case "^" -> stack.push(secondValue.pow(firstValue.intValue()));
                            default -> System.out.println("Unsupported operation " + element);
                        }
                    }
                    sb.append("Stack : ").append(dequeBigDecimalFunction.apply(stack)).append("\n").append(LINE_DECORATOR);
                });

        sb.append("Result : ");
        final String result = dequeBigDecimalFunction.apply(stack);
        sb.append(result);
        return new Result(sb.append("\n\n\n").toString(), result);

    }

    private int getWeight(final String symbol) {
        if ("(".equals(symbol)) return 0;
        if ("+".equals(symbol) || "-".equals(symbol)) return 1;
        if ("/".equals(symbol) || "*".equals(symbol)) return 2;
        if ("^".equals(symbol)) return 4;
        if (")".equals(symbol)) return 5;
        return -1;
    }

    public record Result(String consoleOutput, String resultOutput) {
    }
}
