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

public class ModulusTable {

    private final List<ModulusRule> rules;

    public ModulusTable(List<ModulusRule> rules) {
        this.rules = Collections.unmodifiableList(rules);
    }

    List<ModulusRule> getRules() {
        return rules;
    }

    public List<ModulusRule> getRules(SortCode sortCode) {
        int intSortCode = sortCode.toInt();
        return rules.stream().filter(o -> o.getSortCodeRange().contains(intSortCode)).collect(Collectors.toList());
    }

    public static ModulusTable load() throws IOException {
        try (InputStream in = ModulusTable.class.getResourceAsStream("/valacdos.txt")) {
            return load(in);
        }
    }

    public static ModulusTable load(InputStream in) throws UnsupportedEncodingException {
        Stream<String> lines = new BufferedReader(new InputStreamReader(in, "utf-8")).lines();
        return new ModulusTable(lines
                .filter(o -> !o.trim().isEmpty())
                .map(ModulusRule::parse)
                .collect(Collectors.toList()));
    }
}
