package com.nls.bank;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

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

    public String getMethod() {
        return method;
    }

    public int getModulus() {
        return modulus;
    }

    public IntFunction<? extends IntStream> getFunction() {
        return function;
    }
}
