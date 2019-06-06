package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;

public class Exception10Handler extends ModulusOrHandler {
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        Digits digits = account.toDigits();

        int ab = digits.a() * 10 + digits.b();
        if ((ab == 9 || ab == 99) && digits.g() == 9) {
            digits = digits.zeroise(Digits.U, Digits.B);
        }

        return super.valid(digits, rule);
    }
}
