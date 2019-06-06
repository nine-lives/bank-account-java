package com.nls.bank;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.IntStream;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = -8656334200474299795L;

    private final SortCode sortCode;
    private final AccountNumber accountNumber;

    public BankAccount(String sortCode, String accountNumber) {
        this(new SortCode(sortCode), new AccountNumber(accountNumber));
    }

    public BankAccount(SortCode sortCode, AccountNumber accountNumber) {
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }

    public BankAccount withSortCode(SortCode sortCode) {
        return new BankAccount(sortCode, accountNumber);
    }

    public BankAccount withAccountNumber(String accountNumber) {
        return withAccountNumber(new AccountNumber(accountNumber));
    }

    public BankAccount withAccountNumber(AccountNumber accountNumber) {
        return new BankAccount(sortCode, accountNumber);
    }

    public SortCode getSortCode() {
        return sortCode;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public BankAccount standardise() {
        String an = accountNumber.toString();
        switch (an.length()) {
            case 10:
                if (SortCodeDirectory.isNatwest(sortCode)) {
                    return withAccountNumber(an.substring(2));
                }
                if (SortCodeDirectory.isCoop(sortCode)) {
                    return withAccountNumber(an.substring(0, 8));
                }
                break;
            case 9:
                if (SortCodeDirectory.isSantander(sortCode)) {
                    return new BankAccount(
                            sortCode.toString("").substring(0, 5) + an.charAt(0),
                            an.substring(1));
                }
                break;
        }
        return this;
    }

    public Digits toDigits() {
        return new Digits(toDigitStream().toArray());
    }

    public IntStream toDigitStream() {
        return IntStream.concat(
                Digits.toDigits(sortCode.toString("")),
                Digits.toDigits(accountNumber.toString()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BankAccount that = (BankAccount) o;
        return Objects.equals(sortCode, that.sortCode)
                && Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortCode, accountNumber);
    }

    @Override
    public String toString() {
        return String.format("%s %s", sortCode, accountNumber);
    }
}
