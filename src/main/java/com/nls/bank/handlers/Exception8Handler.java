package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;
import com.nls.bank.SortCode;

public class Exception8Handler extends ModulusAndHandler {
    private static final SortCode SUBSTITUTION = new SortCode("09-01-26");
    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return super.valid(account.withSortCode(SUBSTITUTION), rule);
    }
}
