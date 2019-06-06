package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusCalculator;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationHandler;
import com.nls.bank.ModulusValidationResult;

/**
 * Handler for exception 4 modulus weights
 */
public class Exception4Handler implements ModulusValidationHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        int remainder = ModulusCalculator.remainder(account.toDigits(), rule);
        Digits digits = account.toDigits();
        int check = digits.g() * 10 + digits.h();
        return ModulusValidationResult.and(check == remainder);
    }
}
