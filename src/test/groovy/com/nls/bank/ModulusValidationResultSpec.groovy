package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class ModulusValidationResultSpec extends Specification {

    @Unroll("The results parameters are correct - #type")
    def "The results parameters are correct"() {
        when:
        ModulusValidationResult result = ModulusValidationResult.valueOf(type)

        then:
        result.passed == passed
        result.halt == halt

        where:
        type          | passed | halt
        "PASSED"      | true   | false
        "PASSED_HALT" | true   | true
        "FAILED"      | false  | false
        "FAILED_HALT" | false  | true
    }

    @Unroll("I can check that an or result returns the right result type - #test")
    def "I can check that an or result returns the right result type"() {
        when:
        ModulusValidationResult result = ModulusValidationResult.or(test)

        then:
        result == ModulusValidationResult.valueOf(expected)

        where:
        test          | expected
        true          | "PASSED_HALT"
        false         | "FAILED"
    }

    @Unroll("I can check that an and result returns the right result type - #test")
    def "I can check that an and result returns the right result type"() {
        when:
        ModulusValidationResult result = ModulusValidationResult.and(test)

        then:
        result == ModulusValidationResult.valueOf(expected)

        where:
        test          | expected
        true          | "PASSED"
        false         | "FAILED_HALT"
    }

}
