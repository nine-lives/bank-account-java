package com.nls.bank;

public interface ModulusValidationHandler {
    ModulusValidationResult valid(BankAccount account, ModulusRule rule);
}
