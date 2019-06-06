package com.nls.bank;

/**
 * Interface for modulus validation handlers
 */
public interface ModulusValidationHandler {
    /**
     * Check if an account and rule are valid and return an appropriate response
     * @param account the account
     * @param rule the rule
     * @return the result of the check
     */
    ModulusValidationResult valid(BankAccount account, ModulusRule rule);
}
