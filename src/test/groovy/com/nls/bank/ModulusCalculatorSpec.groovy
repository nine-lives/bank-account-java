package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class ModulusCalculatorSpec extends Specification {
    @Unroll("I can validate the modulus for Double Alternate #sortCode #accountNumber")
    def "I can validate the modulus for Double Alternate"() {
        given:
        ModulusCalculator checker = new ModulusCalculator()
        ModulusRule rule = new ModulusRule(
                new SortCodeRange(0, 100000),
                ModulusMethod.DBLAL, [2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1] as int[],
                Optional.empty())
        BankAccount account = new BankAccount(sortCode, accountNumber)

        when:
        int modulus = checker.modulus(account.toDigits(), rule)
        boolean valid = checker.valid(account.toDigits(), rule)

        then:
        modulusResult == modulus
        validResult == valid

        where:
        sortCode   | accountNumber | modulusResult | validResult
        '49-92-73' | '12345678'    | 70            | true
        '49-92-73' | '12345679'    | 71            | false
    }

    @Unroll("I can validate the modulus for Standard 10 #sortCode #accountNumber")
    def "I can validate the modulus for Standard 10"() {
        given:
        ModulusCalculator checker = new ModulusCalculator()
        ModulusRule rule = new ModulusRule(
                new SortCodeRange(0, 100000),
                ModulusMethod.MOD10, [0, 0, 0, 0, 0, 0, 7, 1, 3, 7, 1, 3, 7, 1] as int[],
                Optional.empty())
        BankAccount account = new BankAccount(sortCode, accountNumber)

        when:
        int modulus = checker.modulus(account.toDigits(), rule)
        boolean valid = checker.valid(account.toDigits(), rule)

        then:
        modulusResult == modulus
        validResult == valid

        where:
        sortCode   | accountNumber | modulusResult | validResult
        '08-99-99' | '66374958'    | 180           | true
        '08-99-99' | '66374959'    | 181           | false
    }

    @Unroll("I can validate the modulus for Standard 11 #sortCode #accountNumber")
    def "I can validate the modulus for Standard 11"() {
        given:
        ModulusCalculator checker = new ModulusCalculator()
        ModulusRule rule = new ModulusRule(
                new SortCodeRange(0, 100000),
                ModulusMethod.MOD11, [0,0,0,0,0,0,8,7,6,5,4,3,2,1] as int[],
                Optional.empty())
        BankAccount account = new BankAccount(sortCode, accountNumber)

        when:
        int modulus = checker.modulus(account.toDigits(), rule)
        boolean valid = checker.valid(account.toDigits(), rule)

        then:
        modulusResult == modulus
        validResult == valid

        where:
        sortCode   | accountNumber | modulusResult | validResult
        '10-79-99' | '88837491'    | 242           | true
        '10-79-99' | '88837492'    | 243           | false
    }
}
