package com.nls.bank

import spock.lang.Specification

class ModulusTableSpec extends Specification {
    def "I can load the modulus table form valacdos.txt"() {
        when:
        ModulusTable table = ModulusTable.load()

        then:
        ModulusRule first = table.getRules().first()
        first.sortCodeRange.start.toInt() == 10004
        first.sortCodeRange.end.toInt() == 16715
        first.method == ModulusMethod.MOD11
        first.weights as List == [0, 0, 0, 0, 0, 0, 8, 7, 6, 5, 4, 3, 2, 1]
        !first.exception.isPresent()

        ModulusRule last = table.getRules().last()
        last.sortCodeRange.start.toInt() == 987004
        last.sortCodeRange.end.toInt() == 989999
        last.method == ModulusMethod.DBLAL
        last.weights as List == [2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1]
        !last.exception.isPresent()

        when:
        List<ModulusRule> rules = table.getRules(new SortCode("20-59-59"))

        then:
        rules.size() == 2
        rules.get(0).getSortCodeRange().contains(205959)
        rules.get(1).getSortCodeRange().contains(205959)
    }
}
