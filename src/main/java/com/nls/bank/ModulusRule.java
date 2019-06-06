package com.nls.bank;

import java.util.Arrays;
import java.util.Optional;

/**
 * A row representing the modulus weights and exceptions. Typically this is just a row from valacdos.txt.
 */
public class ModulusRule {
    private final SortCodeRange sortCodes;
    private final ModulusMethod method;
    private final int[] weights;
    private final Optional<Integer> exception;

    /**
     * Constructor
     * @param sortCodes the sort code range to which this rule is to be applied
     * @param method the algorithm to use
     * @param weights the weights
     * @param exception the exception to apply
     */
    public ModulusRule(SortCodeRange sortCodes, ModulusMethod method, int[] weights, Optional<Integer> exception) {
        this.sortCodes = sortCodes;
        this.method = method;
        this.weights = weights;
        this.exception = exception;
    }

    /**
     * Get the sort code range. A rule is only applied to a bank account only if the sort code is contained within
     * this range
     * @return the range
     */
    public SortCodeRange getSortCodeRange() {
        return sortCodes;
    }

    /**
     * The alogrithm to use
     * @return the alogrithm
     */
    public ModulusMethod getMethod() {
        return method;
    }

    /**
     * The modulus weights
     * @return the weights
     */
    public int[] getWeights() {
        return weights;
    }

    /**
     * The exception to be applied
     * @return the exception
     */
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
