package com.nls.bank;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Immutable representation of a bank account comprising of a sort code and an
 * account number
 */
public class BankAccount implements Serializable {
    private static final long serialVersionUID = -8656334200474299795L;

    private final SortCode sortCode;
    private final AccountNumber accountNumber;

    /**
     * Construct with string representation of the sort code and
     * account number.
     * @param sortCode the sort code
     * @param accountNumber the account number
     */
    public BankAccount(String sortCode, String accountNumber) {
        this(new SortCode(sortCode), new AccountNumber(accountNumber));
    }

    /**
     * Construct with the sort code and
     * account number.
     * @param sortCode the sort code
     * @param accountNumber the account number
     */
    public BankAccount(SortCode sortCode, AccountNumber accountNumber) {
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
    }

    /**
     * Create a new bank account instance with a new sort code and
     * the account number of this instance
     * @param sortCode new sort code
     * @return new BankAccount instance with the new sort code
     */
    public BankAccount withSortCode(SortCode sortCode) {
        return new BankAccount(sortCode, accountNumber);
    }

    /**
     * Create a new bank account instance with a new account number and
     * the sort code of this instance
     * @param accountNumber string representation of the new account number
     * @return new BankAccount instance with the new account number
     */
    public BankAccount withAccountNumber(String accountNumber) {
        return withAccountNumber(new AccountNumber(accountNumber));
    }

    /**
     * Create a new bank account instance with a new account number and
     * the sort code of this instance
     * @param accountNumber the new account number
     * @return new BankAccount instance with the new account number
     */
    public BankAccount withAccountNumber(AccountNumber accountNumber) {
        return new BankAccount(sortCode, accountNumber);
    }

    /**
     * Get the sort code
     * @return the sort code
     */
    public SortCode getSortCode() {
        return sortCode;
    }

    /**
     * Get the account number
     * @return the account number
     */
    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    /**
     * Standardise Non-Standard Natwest, CoOp and Santander bank account details to
     * 8 digit account numbers and return a new instance. If not standarisation is
     * required or no rules exist then this instance is returned
     * @return a standardised version of the bank account or this
     */
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
                if (SortCodeDirectory.isLeeds(sortCode)) {
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

    /**
     * Get the digits of the sort code and account number concatenated. Note that you should
     * call standardise before calling toDigits as the Digits object will only accept 8 digit
     * accounts
     * @return a Digits instance
     */
    public Digits toDigits() {
        return new Digits(toDigitStream().toArray());
    }

    /**
     * Get the digits of the sort code and account number concatenated as an IntStream
     * @return an IntStream of the digits in the sort code and account number
     */
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

    /**
     * The string representation of the bank account as sort-code account-number,
     * i.e. ss-ss-ss aaaaaaaa
     * @return the string representation of the bank account
     */
    @Override
    public String toString() {
        return String.format("%s %s", sortCode, accountNumber);
    }
}
