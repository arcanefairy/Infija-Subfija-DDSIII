package convertirexp;

import java.util.*;

public class ConvertirExp {

    private static Map<Character, Integer> precedencia = new HashMap<>();

    static {
        precedencia.put('+', 1);
        precedencia.put('-', 1);
        precedencia.put('*', 2);
        precedencia.put('/', 2);
        precedencia.put('^', 3);
    }

    public static String infixToPostfix(String infijo) {
        StringBuilder sufijo = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char token : infijo.toCharArray()) {
            if (token == '(') {
                stack.push(token);
            } else if (token == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sufijo.append(stack.pop());
                }
                stack.pop(); // Pop the '('
            } else if (esOperador(token)) {
                while (!stack.isEmpty() && precedencia.get(token) <= precedencia.getOrDefault(stack.peek(), 0)) {
                    sufijo.append(stack.pop());
                }
                stack.push(token);
            } else {
                sufijo.append(token); 
            }
        }

        while (!stack.isEmpty()) {
            sufijo.append(stack.pop());
        }

        return sufijo.toString();
    }

    private static boolean esOperador(char c) {
        return precedencia.containsKey(c);
    }

    public static void main(String[] args) {
        String expresionInfija = "3 + (4 * 2) / (1 - 5)^2";
        String expresionSufija = infixToPostfix(expresionInfija);
        System.out.println("Expresion Infija: " + expresionInfija);
        System.out.println("Expresion Sufija: " + expresionSufija);
    }
}
