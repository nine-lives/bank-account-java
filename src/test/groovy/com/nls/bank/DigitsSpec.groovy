package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.IntStream

class DigitsSpec extends Specification {
    def "I can access the digits passed in"() {
        when:
        Digits digits = new Digits([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[])

        then:
        digits.u() == 0
        digits.v() == 1
        digits.w() == 2
        digits.x() == 3
        digits.y() == 4
        digits.z() == 5
        digits.a() == 6
        digits.b() == 7
        digits.c() == 8
        digits.d() == 9
        digits.e() == 10
        digits.f() == 11
        digits.g() == 12
        digits.h() == 13
    }

    @Unroll("I get an error if an incorrect number of digits are passed in - #count")
    def "I get an error if an incorrect number of digits are passed in"() {
        given:
        int[] digits = new int[count]

        when:
        new Digits(digits)

        then:
        thrown(IllegalArgumentException)

        where:
        count << [13, 15]
    }

    @Unroll("I can zeroise digits - #start to #end")
    def "I can zeroise digits"() {
        given:
        int[] a = new int[14]
        Arrays.fill(a, 1)

        when:
        Digits digits = new Digits(a).zeroise(start, end)

        then:
        Arrays.equals(digits.get(), expected as int[])

        where:
        start | end | expected
        0     | 0   | [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        0     | 1   | [0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
        5     | 8   | [1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1]
        12    | 13  | [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0]
        13    | 13  | [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0]
        13    | 14  | [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0]
        13    | 0   | [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
    }

    @Unroll("I can shift digits - #start to #end, shift - #shift")
    def "I can shift digits"() {
        given:
        int[] a = (0..13).collect() as int[]

        when:
        Digits digits = new Digits(a).shift(start, end, shift, 99)

        then:
        Arrays.equals(digits.get(), expected as int[])

        where:
        start | end | shift | expected
        0     | 0   | 0     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        0     | 0   | 1     | [99, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        0     | 0   | 2     | [99, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        0     | 3   | 1     | [99, 0, 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        0     | 3   | 5     | [99, 99, 99, 99, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        5     | 10  | 2     | [0, 1, 2, 3, 4, 99, 99, 5, 6, 7, 8, 11, 12, 13]
        13    | 13  | 0     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        12    | 13  | 1     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 99, 12]
        13    | 13  | 1     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 99]
        13    | 13  | 5     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 99]
        13    | 15  | 1     | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 99]
        0     | 0   | -1    | [99, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
        5     | 10  | -2    | [0, 1, 2, 3, 4, 7, 8, 9, 10, 99, 99, 11, 12, 13]
        13    | 13  | -1    | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 99]
        10    | 13  | -1    | [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 13, 99]
    }

    def "I can covert a string of digits"() {
        when:
        int[] result = Digits.toDigits("12345678").toArray()

        then:
        Arrays.equals(result, [1, 2, 3, 4, 5, 6, 7, 8] as int[])

    }

    def "I can covert an integer to constituent digits"() {
        when:
        int[] result = Digits.toDigits(12345678).toArray()

        then:
        Arrays.equals(result, [1, 2, 3, 4, 5, 6, 7, 8] as int[])
    }

    def "I can use toString"() {
        when:
        String result = new Digits([0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13] as int[])

        then:
        result == "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]"
    }
}