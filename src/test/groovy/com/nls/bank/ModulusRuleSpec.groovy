package com.nls.bank

import spock.lang.Specification

class ModulusRuleSpec extends Specification {
    def "I can parse a weights line from valacdos.txt without an exception"() {

        given:
        String line = '877099 879999 MOD11    0    0    5   10    9    8    0    7    6    5    4    3    2    1'

        when:

        ModulusRule weights = ModulusRule.parse(line);

        then:
        weights.sortCodeRange.start.toInt() == 877099
        weights.sortCodeRange.end.toInt() == 879999
        weights.method == ModulusMethod.MOD11
        weights.weights as List == [0, 0, 5, 10, 9, 8, 0, 7, 6, 5, 4, 3, 2, 1]
        !weights.exception.isPresent()
    }

    def "I can parse a weights line from valacdos.txt with an exception"() {
        given:
        String line = '877099 879999 MOD11    0    0    5   10    9    8    0    7    6    5    4    3    2    1  11'

        when:

        ModulusRule weights = ModulusRule.parse(line);

        then:
        weights.sortCodeRange.start.toInt() == 877099
        weights.sortCodeRange.end.toInt() == 879999
        weights.method == ModulusMethod.MOD11
        weights.weights as List == [0, 0, 5, 10, 9, 8, 0, 7, 6, 5, 4, 3, 2, 1]
        weights.exception.get() == 11
    }
}

