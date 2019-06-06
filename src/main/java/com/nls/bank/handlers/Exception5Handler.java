package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusCalculator;
import com.nls.bank.ModulusMethod;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationHandler;
import com.nls.bank.ModulusValidationResult;

/**
 * Handler for exception 5 modulus weights
 */
public class Exception5Handler implements ModulusValidationHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        int remainder = ModulusCalculator.remainder(account.toDigits(), rule);
        Digits digits = account.toDigits();
        if (rule.getMethod() != ModulusMethod.DBLAL) {
            if (remainder == 1) {
                return ModulusValidationResult.FAILED_HALT;
            }
            return checkdigit(remainder, digits.g(), 11);
        } else {
            return checkdigit(remainder, digits.h(), 10);
        }
    }

    private ModulusValidationResult checkdigit(int remainder, int checkdigit, int modulus) {
        int check = (modulus - remainder) % modulus;
        return ModulusValidationResult.and(checkdigit == check);
    }
}
