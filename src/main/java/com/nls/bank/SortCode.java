package com.nls.bank;

import java.io.Serializable;

/**
 * Immutable representation of a sort code.
 */
public class SortCode implements Serializable {
    private static final String DEFAULT_DELIMITER = "-";
    private static final long serialVersionUID = -1007866994519062416L;

    private int sortCode;

    /**
     * Construct the sort code from a string. The sort code may be in the following formats, nn-nn-nn, nnnnnn or nn nn nn.
     * @param sortCode the sort code
     */
    public SortCode(String sortCode) {
        if (!sortCode.matches("\\d{2}[- ]?\\d{2}[- ]?\\d{2}")) {
            throw new IllegalArgumentException(String.format("Invalid sort code format - '%s'", sortCode));
        }
        this.sortCode = Integer.parseInt(sortCode.replaceAll("[- ]", ""));
    }

    /**
     * Construct a sort code from an integer. The integer must be less than 1000000 and greater than 0
     * @param sortCode the sort code
     */
    public SortCode(int sortCode) {
        if (sortCode < 0 || sortCode > 999999) {
            throw new IllegalArgumentException(String.format("Invalid sort code size - '%s'", sortCode));
        }
        this.sortCode = sortCode;
    }

    /**
     * Get the sort code as an integer
     * @return the integer value of the sort code
     */
    public int toInt() {
        return sortCode;
    }

    /**
     * Get the string representation of the sort code with the specified delimiter between every pair
     * of digits
     * @param delimiter the delimiter, which can be the empty string
     * @return the string representation of the sort code
     */
    public String toString(String delimiter) {
        return String.format("%02d%s%02d%s%02d",
                sortCode / 10000,
                delimiter,
                (sortCode / 100) % 100,
                delimiter,
                sortCode % 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SortCode that = (SortCode) o;
        return sortCode == that.sortCode;
    }

    @Override
    public int hashCode() {
        return sortCode;
    }

    /**
     * Get the string representation of the sort code with a hyphen as a delimiter between each pair of digits
     * of digits
     * @return the string representation of the sort code
     */
    @Override
    public String toString() {
        return toString(DEFAULT_DELIMITER);
    }
}
