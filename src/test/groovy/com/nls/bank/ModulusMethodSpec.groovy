package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class ModulusMethodSpec extends Specification {

    @Unroll("I can validate that the modulus alogorithms are correct for #name")
    def "I can validate that the modulus alogorithms are correct"() {
        when:
        ModulusMethod method = ModulusMethod.valueOf(type)
        int[] result = method.getFunction().apply(12).toArray()

        then:
        method.method == name
        method.modulus == modulus
        Arrays.equals(result, expected as int[])

        where:
        name               | type    | modulus | expected
        "Standard 10"      | "MOD10" | 10      | [12]
        "Standard 11"      | "MOD11" | 11      | [12]
        "Double Alternate" | "DBLAL" | 10      | [1, 2]
    }
}
