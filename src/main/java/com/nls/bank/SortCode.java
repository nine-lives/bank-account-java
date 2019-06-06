package com.nls.bank;

import java.io.Serializable;

public class SortCode implements Serializable {
    private static final String DEFAULT_DELIMITER = "-";
    private static final long serialVersionUID = -1007866994519062416L;

    private int sortCode;

    public SortCode(String sortCode) {
        if (!sortCode.matches("\\d{2}[- ]?\\d{2}[- ]?\\d{2}")) {
            throw new IllegalArgumentException(String.format("Invalid sort code format - '%s'", sortCode));
        }
        this.sortCode = Integer.parseInt(sortCode.replaceAll("[- ]", ""));
    }

    public SortCode(int sortCode) {
        if (sortCode < 0 || sortCode > 999999) {
            throw new IllegalArgumentException(String.format("Invalid sort code size - '%s'", sortCode));
        }
        this.sortCode = sortCode;
    }

    public int toInt() {
        return sortCode;
    }

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

    @Override
    public String toString() {
        return toString(DEFAULT_DELIMITER);
    }
}
