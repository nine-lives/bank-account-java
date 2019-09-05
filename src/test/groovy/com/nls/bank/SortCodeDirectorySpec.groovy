package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class SortCodeDirectorySpec extends Specification {
    @Unroll("I can check if it is a Natwest sort code - #sortCode")
    def "I can check if it is a Natwest sort code"() {
        when:
        boolean result = SortCodeDirectory.isNatwest(new SortCode(sortCode))

        then:
        result == expected

        where:
        sortCode | expected
        '00-00-00' | false
        '01-00-00' | true
        '01-99-99' | true
        '02-00-00' | false
        '10-00-00' | false
        '49-99-99' | false
        '50-00-00' | true
        '59-99-99' | true
        '60-00-00' | true
        '66-99-99' | true
        '70-00-00' | false
    }

    @Unroll("I can check if it is a CoOp sort code - #sortCode")
    def "I can check if it is a CoOp sort code"() {
        when:
        boolean result = SortCodeDirectory.isCoop(new SortCode(sortCode))

        then:
        result == expected

        where:
        sortCode | expected
        '00-00-00' | false
        '08-00-00' | true
        '08-99-99' | true
        '09-00-00' | false
        '08-29-99' | true
        '08-30-00' | false
        '08-39-99' | false
        '08-40-00' | true
    }

    @Unroll("I can check if it is a Leeds Building Society sort code - #sortCode")
    def "I can check if it is a Leeds Building Society sort code"() {
        when:
        boolean result = SortCodeDirectory.isLeeds(new SortCode(sortCode))

        then:
        result == expected

        where:
        sortCode | expected
        '08-61-18' | false
        '08-61-19' | true
        '08-61-20' | false
    }

    @Unroll("I can check if it is a Santander sort code - #sortCode")
    def "I can check if it is a Santander sort code"() {
        when:
        boolean result = SortCodeDirectory.isSantander(new SortCode(sortCode))

        then:
        result == expected

        where:
        sortCode | expected
        '00-00-00' | false
        '09-00-00' | true
        '09-19-99' | true
        '09-20-00' | false
        '71-99-99' | false
        '72-00-00' | true
        '72-99-99' | true
        '73-00-00' | false
        '88-99-99' | false
        '89-00-00' | true
        '89-29-99' | true
        '89-30-00' | false
    }
}
