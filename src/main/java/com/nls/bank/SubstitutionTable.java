package com.nls.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Table of all the sort code substition lookups. The default loader loads these from the scsubtab.txt resource in
 * the library, though it is possible to load from other sources or construct the lookup programmatically.
 */

public class SubstitutionTable {
    private final Map<Integer, Integer> substitutions;

    /**
     * Constructor
     * @param substitutions the substitution map
     */
    public SubstitutionTable(Map<Integer, Integer> substitutions) {
        this.substitutions = substitutions;
    }

    /**
     * Get the substitution for the sort code. If no substitution is found then returns the supplied sort code
     * @param sortCode the sort code to substitute
     * @return the substituted sort code or the supplied parameter is none found
     */
    public int get(int sortCode) {
        return substitutions.getOrDefault(sortCode, sortCode);
    }

    /**
     * Get the substitution for the sort code. If no substitution is found then returns the supplied sort code
     * @param sortCode the sort code to substitute
     * @return the substituted sort code or the supplied parameter is none found
     */
    public SortCode get(SortCode sortCode) {
        int sub = get(sortCode.toInt());
        return sortCode.toInt() == sub ? sortCode : new SortCode(sub);
    }

    /**
     * Get the substitution for the sort code in the bank account. If no substitution is found then returns
     * the supplied bank account
     * @param bankAccount the bank account with the sort code to substitute
     * @return a new bank account instance with the substituted sort code or the supplied instance is none found
     */
    public BankAccount get(BankAccount bankAccount) {
        int sub = get(bankAccount.getSortCode().toInt());
        return bankAccount.getSortCode().toInt() == sub ? bankAccount : bankAccount.withSortCode(new SortCode(sub));
    }

    /**
     * Load the lookup from the scsubtab.txt resource in the library
     * @return a modulus table
     * @throws IOException
     */
    public static SubstitutionTable load() throws IOException {
        try (InputStream in = ModulusTable.class.getResourceAsStream("/scsubtab-2005-06-13.txt")) {
            return load(in);
        }
    }

    /**
     * Load the lookup from an input stream. The loader expects the format of the data in the input stream
     * to match the format of scsubtab.txt. It is the caller's responsibility to ensure the stream is closed
     * @return a modulus table
     * @throws UnsupportedEncodingException
     */
    public static SubstitutionTable load(InputStream in) throws UnsupportedEncodingException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(in, "utf-8")).lines();
        return new SubstitutionTable(lines
                .filter(o -> !o.trim().isEmpty())
                .map(o -> o.split("\\s+"))
                .collect(Collectors.toMap(o -> Integer.parseInt(o[0]), o -> Integer.parseInt(o[1]))));
    }

}
