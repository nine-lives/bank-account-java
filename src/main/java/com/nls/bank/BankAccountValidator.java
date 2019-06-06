package com.nls.bank;

import java.io.IOException;
import java.util.List;

public class BankAccountValidator {

    private final ModulusTable modulusTable;
    private final SubstitutionTable substitutionTable;
    private final ModulusValidationHandlerProvider handlerProvider;

    public BankAccountValidator() throws IOException {
        this(ModulusTable.load(), SubstitutionTable.load(), ModulusValidationHandlerProvider.getInstance());
    }

    public BankAccountValidator(
            ModulusTable modulusTable,
            SubstitutionTable substitutionTable,
            ModulusValidationHandlerProvider handlerProvider) {
        this.modulusTable = modulusTable;
        this.substitutionTable = substitutionTable;
        this.handlerProvider = handlerProvider;
    }

    public boolean valid(String sortCode, String accountNumber) {
        return valid(new BankAccount(sortCode, accountNumber));
    }

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
