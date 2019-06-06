package com.nls.bank

import spock.lang.Specification

class SubstitutionTableSpec extends Specification {
    def "I can load the modulus table from scsubtab.txt"() {
        when:
        SubstitutionTable table = SubstitutionTable.load()

        then:
        table.get(938173) == 938017
        table.get(new SortCode(938173)) == new SortCode("93-80-17")
        table.get(new BankAccount("93-81-73", "12345678")) == new BankAccount("93-80-17", "12345678")
    }
}
