package com.nls.bank

import spock.lang.Specification
import spock.lang.Unroll

class BankAccountSpec extends Specification {
    def "I can create a bank account"() {
        given:
        SortCode sortCode = new SortCode("12-34-45")
        AccountNumber accountNumber = new AccountNumber("87654321")

        when:
        BankAccount account = new BankAccount(sortCode, accountNumber)

        then:
        sortCode == account.sortCode
        accountNumber == account.accountNumber
    }

    def "I can create a new account with a different sort code"() {
        given:
        SortCode sortCodeOriginal = new SortCode("12-34-45")
        SortCode sortCodeNew = new SortCode("21-43-54")
        AccountNumber accountNumber = new AccountNumber("87654321")
        BankAccount accountOriginal = new BankAccount(sortCodeOriginal, accountNumber)

        when:
        BankAccount accountNew = accountOriginal.withSortCode(sortCodeNew)

        then:
        sortCodeOriginal == accountOriginal.sortCode
        accountNumber == accountOriginal.accountNumber
        sortCodeNew == accountNew.sortCode
        accountNumber == accountNew.accountNumber

        accountOriginal == new BankAccount(sortCodeOriginal, accountNumber)
        accountOriginal != accountNew
    }


    def "I can convert the sort code and account number to a digit stream"() {
        given:
        SortCode sortCode = new SortCode("12-34-45")
        AccountNumber accountNumber = new AccountNumber("87654321")
        BankAccount account = new BankAccount(sortCode, accountNumber)

        when:
        List<Integer> digits = account.toDigitStream().toArray() as List

        then:
        digits == [1, 2, 3, 4, 4, 5, 8, 7, 6, 5, 4, 3, 2, 1]
    }

    @Unroll("I can standardise irregular account numbers - #bank")
    def "I can standardise irregular account numbers"() {
        given:
        BankAccount account = new BankAccount(sortCode, accountNumber)

        when:
        BankAccount result = account.standardise()

        then:
        result.sortCode == new SortCode(expectedSortCode)
        result.accountNumber == new AccountNumber(expectedAccountNumber)
        result.accountNumber.toString() == expectedAccountNumber
        result.toString() == expectedSortCode + ' ' + expectedAccountNumber

        where:
        bank           | sortCode   | accountNumber | expectedSortCode | expectedAccountNumber
        'Natwest 01'   | '01-00-00' | '0123456789'  | '01-00-00'       | '23456789'
        'Natwest 50'   | '50-00-00' | '0123456789'  | '50-00-00'       | '23456789'
        'Coop 08'      | '08-60-00' | '1234567890'  | '08-60-00'       | '12345678'
        'Leeds 08'     | '08-61-19' | '1234567890'  | '08-61-19'       | '12345678'
        'Santander 09' | '09-19-00' | '123456789'   | '09-19-01'       | '23456789'
        'Santander 72' | '72-00-00' | '123456789'   | '72-00-01'       | '23456789'
        'Santander 89' | '89-29-99' | '123456789'   | '89-29-91'       | '23456789'
        'Short 6'      | '01-00-00' | '123456'      | '01-00-00'       | '00123456'
        'Short 7'      | '01-00-00' | '1234567'     | '01-00-00'       | '01234567'
    }

    def "I can compare bank account"() {
        when:
        BankAccount ba1 = new BankAccount("123456", "12345678")
        BankAccount ba2 = new BankAccount("12-34-56","12345678")
        BankAccount ba3 = new BankAccount("65-43-21", "12345678")
        BankAccount ba4 = new BankAccount("123456", "87654321")

        then:
        ba1.equals(ba1)
        ba1 == ba2
        ba2 == ba1
        ba1 != ba3
        ba1 != ba4
        ba1 == ba1
        ba1 != "12-34-56"
        ba1 != null

        and:
        ba1.hashCode() == ba2.hashCode()
        ba1.hashCode() != ba3.hashCode()
        ba1.hashCode() != ba4.hashCode()
    }

}
