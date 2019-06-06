package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.ModulusCalculator;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationHandler;
import com.nls.bank.ModulusValidationResult;

/**
 * Handler for exception 1 modulus weights
 */
public class Exception1Handler implements ModulusValidationHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return ModulusValidationResult.and(ModulusCalculator.valid(account.toDigits(), rule, 27));
    }
}
