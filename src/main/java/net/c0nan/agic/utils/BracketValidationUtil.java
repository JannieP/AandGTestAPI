package net.c0nan.agic.utils;

import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BracketValidationUtil {

    private static Map<Character, Character> bracketPairs = new HashMap<>();
    private static List<Character> openBrackets = new ArrayList<>();
    private static List<Character> closeBrackets = new ArrayList<>();

    static {

        bracketPairs.put(')', '(');
        bracketPairs.put('}', '{');
        bracketPairs.put(']', '[');

        openBrackets.add('{');
        openBrackets.add('[');
        openBrackets.add('(');

        closeBrackets.add('}');
        closeBrackets.add(']');
        closeBrackets.add(')');

    }

    public static boolean matchBrackets(final String value) {
        if (value.isEmpty()) {
            return true;
        }

        ArrayDeque<Character> queue = new ArrayDeque<>();
        for (Character charz : value.toCharArray()) {
            if (openBrackets.contains(charz)) {
                queue.add(charz);
            }
            if (closeBrackets.contains(charz)) {
                if (queue.isEmpty() || queue.getLast() != bracketPairs.get(charz)) {
                    return false;
                }
                queue.removeLast();
            }
        }

        return queue.isEmpty();

    }

}
