package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;

public class Exception6Handler extends ModulusAndHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        Digits digits = account.toDigits();
        if (digits.a() >= 4 && digits.a() <= 8 && digits.g() == digits.h()) {
            return ModulusValidationResult.PASSED_HALT;
        }
        return super.valid(account, rule);
    }
}
