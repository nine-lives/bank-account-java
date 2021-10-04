package com.nls.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Table of all the modulus rules. The default loader loads these from the valacdos.txt resource in the library, though
 * it is possible to load from other sources or construct the rules programmatically.
 */
public class ModulusTable {

    private final List<ModulusRule> rules;

    /**
     * Constructor
     * @param rules a list of the rules
     */
    public ModulusTable(List<ModulusRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    /**
     * All the rules in the table
     * @return a list of the rules
     */
    public List<ModulusRule> getRules() {
        return rules;
    }

    /**
     * Get a list of the rules that match the supplied sort code.
     * @param sortCode the sort code
     * @return a list of matching rules
     */
    public List<ModulusRule> getRules(SortCode sortCode) {
        int intSortCode = sortCode.toInt();
        return rules.stream().filter(o -> o.getSortCodeRange().contains(intSortCode)).collect(Collectors.toList());
    }

    /**
     * Load the rules from the valacdos.txt resource in the library
     * @return a modulus table
     * @throws IOException
     */
    public static ModulusTable load() throws IOException {
        try (InputStream in = ModulusTable.class.getResourceAsStream("/valacdos-2021-09-06.txt")) {
            return load(in);
        }
    }

    /**
     * Load the rules from an input stream. The loader expects the format of the data in the input stream
     * to match the format of valacdos.txt. It is the caller's responsibility to ensure the stream is closed
     * @return a modulus table
     * @throws UnsupportedEncodingException
     */
    public static ModulusTable load(InputStream in) throws UnsupportedEncodingException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(in, "utf-8")).lines();
        return new ModulusTable(lines
                .filter(o -> !o.trim().isEmpty())
                .map(ModulusRule::parse)
                .collect(Collectors.toList()));
    }
}
