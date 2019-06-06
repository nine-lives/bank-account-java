package com.nls.bank;

import java.util.Arrays;
import java.util.Optional;

public class ModulusRule {
    private final SortCodeRange sortCodes;
    private final ModulusMethod method;
    private final int[] weights;
    private final Optional<Integer> exception;

    public ModulusRule(SortCodeRange sortCodes, ModulusMethod method, int[] weights, Optional<Integer> exception) {
        this.sortCodes = sortCodes;
        this.method = method;
        this.weights = weights;
        this.exception = exception;
    }

    public SortCodeRange getSortCodeRange() {
        return sortCodes;
    }

    public ModulusMethod getMethod() {
        return method;
    }

    public int[] getWeights() {
        return weights;
    }

    public Optional<Integer> getException() {
        return exception;
    }

    static ModulusRule parse(String line) {
        String[] tokens = line.split("\\s+");
        return new ModulusRule(
            new SortCodeRange(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1])),
            ModulusMethod.valueOf(tokens[2]),
            Arrays.stream(Arrays.copyOfRange(tokens, 3, 17)).mapToInt(Integer::parseInt).toArray(),
            Optional.ofNullable(tokens.length == 17 ? null : Integer.parseInt(tokens[17])));
    }
}
