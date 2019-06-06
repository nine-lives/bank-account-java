package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;

/**
 * Handler for exception 3 modulus weights
 */
public class Exception3Handler extends ModulusAndHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        int c = account.toDigits().c();
        if (c == 6 || c == 9) {
            return ModulusValidationResult.PASSED;
        }
        return super.valid(account, rule);
    }
}
