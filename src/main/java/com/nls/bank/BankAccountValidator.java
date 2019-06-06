package com.nls.bank;

import java.io.IOException;
import java.util.List;

/**
 * Validator that performs modulus checking of bank account numbers.
 */
public class BankAccountValidator {

    private final ModulusTable modulusTable;
    private final SubstitutionTable substitutionTable;
    private final ModulusValidationHandlerProvider handlerProvider;

    /**
     * Default constructor that loads the default modulus weight table, the default substitution table and
     * the default instance modulus validator handler provider. This is equivalent to calling
     * new BankAccountValidator(ModulusTable.load(), SubstitutionTable.load(), ModulusValidationHandlerProvider.getInstance())
     * @throws IOException
     */
    public BankAccountValidator() throws IOException {
        this(ModulusTable.load(), SubstitutionTable.load(), ModulusValidationHandlerProvider.getInstance());
    }

    /**
     * Constructor that allows customisation of the modulus weight table, the substitution table and
     * modulus validator handler provider.
     * @param modulusTable the modulus weight table
     * @param substitutionTable the substitution table
     * @param handlerProvider the modulus validator handler provider
     */
    public BankAccountValidator(
            ModulusTable modulusTable,
            SubstitutionTable substitutionTable,
            ModulusValidationHandlerProvider handlerProvider) {
        this.modulusTable = modulusTable;
        this.substitutionTable = substitutionTable;
        this.handlerProvider = handlerProvider;
    }

    /**
     * Check if the bank account numbers pass the modulus check
     * @param sortCode the sort code
     * @param accountNumber the account number
     * @return true if valid, false otherwise
     */
    public boolean valid(String sortCode, String accountNumber) {
        try {
            return valid(new BankAccount(sortCode, accountNumber));
        } catch (IllegalArgumentException ignore) {
            return false;
        }
    }

    /**
     * Check if the bank account numbers pass the modulus check
     * @param account the bank account
     * @return true if valid, false otherwise
     */
    public boolean valid(BankAccount account) {
        BankAccount standardisedAccount = substitutionTable.get(account).standardise();
        List<ModulusRule> rules = modulusTable.getRules(account.getSortCode());
        ModulusValidationResult result = ModulusValidationResult.PASSED;
        for (ModulusRule rule : rules) {
            result = handlerProvider.get(rule).valid(standardisedAccount, rule);
            if (result.isHalt()) {
                return result.isPassed();
            }
        }
        return result.isPassed();
    }
}
