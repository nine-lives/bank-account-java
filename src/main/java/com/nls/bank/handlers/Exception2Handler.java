package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.Digits;
import com.nls.bank.ModulusMethod;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;
import com.nls.bank.SortCodeRange;

import java.util.Optional;

public class Exception2Handler extends ModulusOrHandler {
    private static final ModulusRule ALT_RULE_1 = new ModulusRule(
            new SortCodeRange(0, 999999),
            ModulusMethod.MOD11,
            new int[] {0, 0, 1, 2, 5, 3, 6, 4, 8, 7, 10, 9, 3, 1 },
            Optional.empty());

    private static final ModulusRule ALT_RULE_2 = new ModulusRule(
            new SortCodeRange(0, 999999),
            ModulusMethod.MOD11,
            new int[] {0, 0, 0, 0, 0, 0, 0, 0, 8, 7, 10, 9, 3, 1 },
            Optional.empty());

    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return super.valid(account, pickRule(account, rule));
    }

    private ModulusRule pickRule(BankAccount account, ModulusRule rule) {
        Digits digits = account.toDigits();
        if (digits.a() != 0) {
            return digits.g() == 9 ? ALT_RULE_2 : ALT_RULE_1;
        }
        return rule;
    }
}
