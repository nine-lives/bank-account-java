package com.nls.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubstitutionTable {
    private final Map<Integer, Integer> substitutions;

    public SubstitutionTable(Map<Integer, Integer> substitutions) {
        this.substitutions = substitutions;
    }

    public int get(int sortCode) {
        return substitutions.getOrDefault(sortCode, sortCode);
    }

    public SortCode get(SortCode sortCode) {
        int sub = get(sortCode.toInt());
        return sortCode.toInt() == sub ? sortCode : new SortCode(sub);
    }

    public BankAccount get(BankAccount bankAccount) {
        int sub = get(bankAccount.getSortCode().toInt());
        return bankAccount.getSortCode().toInt() == sub ? bankAccount : bankAccount.withSortCode(new SortCode(sub));
    }

    public static SubstitutionTable load() throws IOException {
        try (InputStream in = ModulusTable.class.getResourceAsStream("/scsubtab.txt")) {
            return load(in);
        }
    }

    public static SubstitutionTable load(InputStream in) throws UnsupportedEncodingException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(in, "utf-8")).lines();
        return new SubstitutionTable(lines
                .filter(o -> !o.trim().isEmpty())
                .map(o -> o.split("\\s+"))
                .collect(Collectors.toMap(o -> Integer.parseInt(o[0]), o -> Integer.parseInt(o[1]))));
    }

}
