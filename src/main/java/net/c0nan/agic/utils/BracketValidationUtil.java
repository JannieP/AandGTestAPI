package net.c0nan.agic.utils;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class BracketValidationUtil {

    private static Map<Character, Character> bracketPairs = new HashMap<>();

    static {
        bracketPairs.put('(', ')');
        bracketPairs.put('{', '}');
        bracketPairs.put('[', ']');
    }

    public static boolean matchBrackets(final String value) {
        if (value.isEmpty()) {
            return true;
        }
        boolean matching = true;

        Map<Integer, Character> contextSortedBrackets = getContextSortedBrackets(value);

        if (contextSortedBrackets.isEmpty()) {
            matching = validateNoOrphanClosingBrackets(value);
        }

        for (Integer posOpen : contextSortedBrackets.keySet()) {

            Character openBracket = contextSortedBrackets.get(posOpen);

            int posClosure = value.lastIndexOf(bracketPairs.get(openBracket));

            if (posOpen < 0 && posClosure < 0) {
                matching = true;
            } else if (posOpen >= 0 && posOpen < posClosure) {
                if (posOpen > 0) {
                    matching = matchBrackets(value.substring(0, posOpen));
                }
                if (matching) {
                    matching = matchBrackets(value.substring(posOpen + 1, posClosure));
                }
                if (matching && posClosure < value.length() - 1) {
                    matching = matchBrackets(value.substring(posClosure + 1));
                }
                break;
            } else {
                matching = false;
            }

            if (!matching) {
                break;
            }

        }
        return matching;
    }

    private static boolean validateNoOrphanClosingBrackets(final String value) {
        for (Character bracket : bracketPairs.values()) {
            if (value.indexOf(bracket) >= 0) {
                return false;
            }
        }
        return true;
    }

    private static Map<Integer, Character> getContextSortedBrackets(final String value) {
        Map<Integer, Character> contextSortedBrackets = new TreeMap<>();

        for (Character openBracket : bracketPairs.keySet()) {
            int posOpen = value.indexOf(openBracket);
            if (posOpen >= 0) {
                contextSortedBrackets.put(posOpen, openBracket);
            }
        }
        return contextSortedBrackets;
    }
}
