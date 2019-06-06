package com.nls.bank;

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

    public boolean isPassed() {
        return passed;
    }

    public boolean isHalt() {
        return halt;
    }

    public static ModulusValidationResult or(boolean valid) {
        return valid
                ? ModulusValidationResult.PASSED_HALT
                : ModulusValidationResult.FAILED;
    }

    public static ModulusValidationResult and(boolean valid) {
        return valid
                ? ModulusValidationResult.PASSED
                : ModulusValidationResult.FAILED_HALT;
    }

}
