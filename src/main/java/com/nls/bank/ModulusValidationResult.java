package com.nls.bank;

/**
 * Enum representing the result of a validation handler and the action to take
 */
public enum ModulusValidationResult {
    PASSED(true, false),
    PASSED_HALT(true, true),
    FAILED(false, false),
    FAILED_HALT(false, true);

    private final boolean passed;
    private final boolean halt;

    ModulusValidationResult(boolean passed, boolean halt) {
        this.passed = passed;
        this.halt = halt;
    }

    /**
     * Has the check passed
     * @return true if it passed, false otherwise
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Should validation continue or cease immediately
     * @return true to stop further validation, false to continue
     */
    public boolean isHalt() {
        return halt;
    }

    /**
     * Return a result that is equivalent to an OR for validation, i.e. halt validation
     * if valid, and continue validation on failures
     * @param valid was the check valid or not
     * @return the result
     */
    public static ModulusValidationResult or(boolean valid) {
        return valid
                ? ModulusValidationResult.PASSED_HALT
                : ModulusValidationResult.FAILED;
    }

    /**
     * Return a result that is equivalent to an AND for validation, i.e. halt validation
     * on failure, and continue validation if successful
     * @param valid was the check valid or not
     * @return the result
     */
    public static ModulusValidationResult and(boolean valid) {
        return valid
                ? ModulusValidationResult.PASSED
                : ModulusValidationResult.FAILED_HALT;
    }

}
