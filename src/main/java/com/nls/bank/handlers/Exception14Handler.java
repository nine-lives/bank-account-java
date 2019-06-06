package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusCalculator;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationHandler;
import com.nls.bank.ModulusValidationResult;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handler for exception 14 modulus weights
 */
public class Exception14Handler implements ModulusValidationHandler {
    private static final Set<Integer> ACCEPTED_VALUES = Stream.of(0, 1, 9).collect(Collectors.toSet());

    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return ModulusCalculator.valid(account, rule)
                ? ModulusValidationResult.PASSED_HALT
                : secondCheck(account, rule);
    }

    private ModulusValidationResult secondCheck(BankAccount account, ModulusRule rule) {
        Digits digits = account.toDigits();

        if (!ACCEPTED_VALUES.contains(digits.h())) {
            return ModulusValidationResult.FAILED_HALT;
        }

        digits = digits.shift(Digits.A, Digits.H, 1, 0);
        return ModulusCalculator.valid(digits, rule)
                ? ModulusValidationResult.PASSED_HALT
                : ModulusValidationResult.FAILED_HALT;
    }
}
