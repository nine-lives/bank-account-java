package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class SortCodeSpec extends Specification {
    @Unroll("toString formats the number correctly #accountNumber")
    def "toString formats the number correctly"() {
        when:
        SortCode result = new SortCode("123456")

        then:
        result.toString() == "12-34-56"
    }

    @Unroll("I can construct a sort code from a string - #sortCode")
    def "I can construct a sort code from a string"() {
        when:
        SortCode sortCode = new SortCode(value)

        then:
        sortCode.toString() == expected

        where:
        value      | expected
        '000000'   | '00-00-00'
        '00-00-00' | '00-00-00'
        '00 00 00' | '00-00-00'
        '999999'   | '99-99-99'
        '99-99-99' | '99-99-99'
        '99 99 99' | '99-99-99'
    }

    @Unroll("An invalid sort code throws an exception - #sortCode")
    def "An invalid sort code throws an exception"() {
        when:
        new SortCode(sortCode)

        then:
        thrown(IllegalArgumentException)

        where:
        sortCode <<
                ['1-1-1',
                 'abc',
                 '1234',
                 '12-34',
                 '12-34-56-78',
                 '12-34-a']
    }


    @Unroll("I can construct a sort code from an integer - #sortCode")
    def "I can construct a sort code from an integer"() {
        when:
        SortCode sortCode = new SortCode(value)

        then:
        sortCode.toString() == expected
        sortCode.toInt() == value

        where:
        value  | expected
        0      | '00-00-00'
        12345  | '01-23-45'
        123456 | '12-34-56'
        999999 | '99-99-99'
    }

    @Unroll("An invalid sort code as integer throws an exception - #sortCode")
    def "An invalid sort code as integer throws an exception"() {
        when:
        new SortCode(sortCode)

        then:
        thrown(IllegalArgumentException)

        where:
        sortCode <<
                [1000000,
                 -1]
    }

    def "I can compare sort codes"() {
        when:
        SortCode sc1 = new SortCode("123456")
        SortCode sc2 = new SortCode("12-34-56")
        SortCode sc3 = new SortCode("65-43-21")

        then:
        sc1.equals(sc1)
        sc1 == sc2
        sc2 == sc1
        sc1 != sc3
        sc1 == sc1
        sc1 != "12-34-56"
        sc1 != null

        and:
        sc1.hashCode() == sc2.hashCode()
        sc1.hashCode() != sc3.hashCode()
    }
}
