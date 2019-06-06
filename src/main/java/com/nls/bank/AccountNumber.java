package com.nls.bank;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class AccountNumber implements Serializable {
    private static final long serialVersionUID = 4682356730552104145L;

    private final BigInteger accountNumber;
    private final int digits;

    public AccountNumber(String accountNumber) {
        String normalised = accountNumber.replaceAll("[ -]", "");
        if (!normalised.matches("\\d{6,10}")) {
            throw new IllegalArgumentException(String.format("Invalid account number format - '%s'", accountNumber));
        }
        this.accountNumber = new BigInteger(normalised);
        this.digits = Math.max(normalised.length(), 8);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AccountNumber that = (AccountNumber) o;
        return accountNumber.equals(that.accountNumber) && digits == that.digits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, digits);
    }

    @Override
    public String toString() {
        String format = "%1$" + digits + "s";
        return String.format(format, accountNumber.toString())
                .replace(' ', '0');
    }
}
