package net.c0nan.agic.services;

import net.c0nan.agic.exception.ValidationException;
import net.c0nan.agic.models.result.BalanceTestResult;
import net.c0nan.agic.utils.BracketValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static final Integer MAX_LENGTH = 100;

    public BalanceTestResult validateBrackets(final String input) {
        BalanceTestResult balanceTestResult = new BalanceTestResult();
        balanceTestResult.setInput(input);
        balanceTestResult.setBalanced(BracketValidationUtil.matchBrackets(input));
        return balanceTestResult;
    }

    public void validateInput(final String input) {
        if (input.length() > MAX_LENGTH) {
            throw new ValidationException("Must be between 1 and 100 chars long");
        }
    }
}
