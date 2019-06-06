package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class AccountNumberSpec extends Specification {
    @Unroll("toString formats the number correctly #accountNumber")
    def "toString formats the number correctly"() {
        when:
        AccountNumber result = new AccountNumber(accountNumber)

        then:
        result.toString() == expected

        where:
        accountNumber | expected
        '123456'      | '00123456'
        '1234567'     | '01234567'
        '12345678'    | '12345678'
        '12-3456789'   | '123456789'
        '12-34567890'   | '1234567890'
    }

    @Unroll("An invalid account number throws an exception - #accountNumber")
    def "An invalid account number throws an exception"() {
        when:
        new AccountNumber(accountNumber)

        then:
        thrown(IllegalArgumentException)

        where:
        accountNumber <<
        ['12345',
        '12345678901',
        'a1234567',
        '1234567a']
    }

    def "I can compare account numbers"() {
        when:
        AccountNumber an1 = new AccountNumber("1234567")
        AccountNumber an2 = new AccountNumber("1234567")
        AccountNumber an3 = new AccountNumber("7654321")

        then:
        an1.equals(an1)
        an1 == an2
        an2 == an1
        an1 != an3
        an1 == an1
        an1 != "12345678"
        an1 != null

        and:
        an1.hashCode() == an2.hashCode()
        an1.hashCode() != an3.hashCode()
    }

}
