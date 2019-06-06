package com.nls.bank;

import java.util.stream.IntStream;

public final class ModulusCalculator {

    private ModulusCalculator() {

    }

    public static int modulus(Digits digits, ModulusRule rule) {
        int[] weights = rule.getWeights();
        return IntStream.range(0, 14)
                .flatMap(i -> rule.getMethod().getFunction().apply(digits.get(i) * weights[i]))
                .reduce(0, Integer::sum);
    }

    public static int remainder(Digits digits, ModulusRule rule) {
        return remainder(digits, rule, 0);
    }

    public static int remainder(Digits digits, ModulusRule rule, int addToTotal) {
        return (modulus(digits, rule) + addToTotal) % rule.getMethod().getModulus();
    }

    public static boolean valid(BankAccount bankAccount, ModulusRule rule) {
        return valid(bankAccount.toDigits(), rule, 0);
    }

    public static boolean valid(Digits digits, ModulusRule rule) {
        return valid(digits, rule, 0);
    }

    public static boolean valid(Digits digits, ModulusRule rule, int addToTotal) {
        return remainder(digits, rule, addToTotal) == 0;
    }
}
