package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class SortCodeRangeSpec extends Specification {
    def "I can check if a sort code is constructed properly"() {
        when:
        SortCodeRange range = new SortCodeRange(
                new SortCode("20-10-01"),
                new SortCode("20-10-98"));

        then:
        range.getStart() == new SortCode("20-10-01")
        range.getEnd() == new SortCode("20-10-98")
        range.toString() == "20-10-01 to 20-10-98"
    }

    @Unroll("I can check if a sort code is in range - #value")
    def "I can check if a sort code is in range"() {
        given:
        SortCodeRange range = new SortCodeRange(
                new SortCode("20-10-01"),
                new SortCode("20-10-98"));

        when:
        boolean result = range.contains(new SortCode(value))

        then:
        result == expected

        where:
        value      | expected
        "20-10-00" | false
        "20-10-01" | true
        "20-10-50" | true
        "20-10-98" | true
        "20-10-99" | false
    }

    def "I can compare sort code ranges"() {
        when:
        SortCodeRange sr1 = new SortCodeRange(121000, 121100)
        SortCodeRange sr2 = new SortCodeRange("12-10-00", "12-11-00")
        SortCodeRange sr3 = new SortCodeRange("12-20-00", "12-21-00")

        then:
        sr1.equals(sr1)
        sr1 == sr2
        sr2 == sr1
        sr1 != sr3
        sr1 == sr1
        sr1 != "12-10-00 to 12-11-00"
        sr1 != null

        and:
        sr1.hashCode() == sr2.hashCode()
        sr1.hashCode() != sr3.hashCode()
    }
}
