package com.nls.bank;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

/**
 * Enum of the types of moduls algorithms and also supporting methods
 */
public enum ModulusMethod {
    MOD10("Standard 10", 10, IntStream::of),
    MOD11("Standard 11", 11, IntStream::of),
    DBLAL("Double Alternate", 10, Digits::toDigits);

    private final String method;
    private final int modulus;
    private final IntFunction<? extends IntStream> function;

    ModulusMethod(String method, int modulus, IntFunction<? extends IntStream> function) {
        this.method = method;
        this.modulus = modulus;
        this.function = function;
    }

    /**
     * Get the human readable string of the method
     * @return human readable string of the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Get the number to divide by to find the remainder
     * @return the number to divide the modulus total by
     */
    public int getModulus() {
        return modulus;
    }

    /**
     * The function to apply after each weight is multiplied with its corresponding digit before totalling. Typically this is either
     * the number itself or a function to split into individual digits
     * @return the func
     */
    public IntFunction<? extends IntStream> getFunction() {
        return function;
    }
}
