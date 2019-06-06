package com.nls.bank;

import java.util.stream.IntStream;

/**
 * Utility class to calculate the modulus
 */
public final class ModulusCalculator {

    private ModulusCalculator() {

    }

    /**
     * Calculate the modulus for a given set of digits and rule
     * @param digits the digits
     * @param rule the modulus alogrithm, weights and excpetions
     * @return the modulus
     */
    public static int modulus(Digits digits, ModulusRule rule) {
        int[] weights = rule.getWeights();
        return IntStream.range(0, 14)
                .flatMap(i -> rule.getMethod().getFunction().apply(digits.get(i) * weights[i]))
                .reduce(0, Integer::sum);
    }

    /**
     * Calculate the remainder of the modulus for a given set of digits and rule
     * @param digits the digits
     * @param rule the modulus alogrithm, weights and excpetions
     * @return the remainder
     */
    public static int remainder(Digits digits, ModulusRule rule) {
        return remainder(digits, rule, 0);
    }

    /**
     * Calculate the remainder of the modulus for a given set of digits and rule
     * @param digits the digits
     * @param rule the modulus alogrithm, weights and excpetions
     * @param addToTotal a number to add to the modulus befor the remainder is taken
     * @return the remainder
     */
    public static int remainder(Digits digits, ModulusRule rule, int addToTotal) {
        return (modulus(digits, rule) + addToTotal) % rule.getMethod().getModulus();
    }

    /**
     * Calculate if the remainder is 0 for a given bank account and rule
     * @param bankAccount the bank account to validate
     * @param rule the modulus alogrithm, weights and excpetions
     * @return true if modulus remainder is 0, false otherwise
     */
    public static boolean valid(BankAccount bankAccount, ModulusRule rule) {
        return valid(bankAccount.toDigits(), rule, 0);
    }

    /**
     * Calculate if the remainder is 0 for a given digits and rule
     * @param digits the digits
     * @param rule the modulus alogrithm, weights and excpetions
     * @return true if modulus remainder is 0, false otherwise
     */
    public static boolean valid(Digits digits, ModulusRule rule) {
        return valid(digits, rule, 0);
    }

    /**
     * Calculate if the remainder is 0 for a given digits and rule
     * @param digits the digits
     * @param rule the modulus alogrithm, weights and excpetions
     * @param addToTotal a number to add to the modulus befor the remainder is taken
     * @return true if modulus remainder is 0, false otherwise
     */
    public static boolean valid(Digits digits, ModulusRule rule, int addToTotal) {
        return remainder(digits, rule, addToTotal) == 0;
    }
}
