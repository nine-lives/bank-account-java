package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusCalculator;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationHandler;
import com.nls.bank.ModulusValidationResult;

/**
 * Handler that checks modulus is valid and returns result that halts and passes validation
 * if ANY rule passes check
 */
public class ModulusOrHandler implements ModulusValidationHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return valid(account.toDigits(), rule);
    }

    public ModulusValidationResult valid(Digits digits, ModulusRule rule) {
        return ModulusValidationResult.or(ModulusCalculator.valid(digits, rule));
    }
}
