package com.nls.bank.handlers;

import com.nls.bank.BankAccount;
import com.nls.bank.ModulusRule;
import com.nls.bank.ModulusValidationResult;
import com.nls.bank.SortCode;

public class Exception9Handler extends ModulusOrHandler {
    private static final SortCode SUBSTITUTE = new SortCode("30-96-34");

    @Override
    public ModulusValidationResult valid(BankAccount account, ModulusRule rule) {
        return super.valid(account.withSortCode(SUBSTITUTE), rule);
    }
}
