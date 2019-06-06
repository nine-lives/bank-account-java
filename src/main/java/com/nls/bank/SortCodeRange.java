package com.nls.bank;

import java.util.Objects;

/**
 * Class representing a range of sort codes.
 */
public class SortCodeRange {
    private final int start;
    private final int end;


    /**
     * Construct a range from start to end inclusively
     * @param start the start sort code
     * @param end the end sort code
     */
    public SortCodeRange(String start, String end) {
        this(new SortCode(start), new SortCode(end));
    }

    /**
     * Construct a range from start to end inclusively
     * @param start the start sort code
     * @param end the end sort code
     */
    public SortCodeRange(SortCode start, SortCode end) {
        this(start.toInt(), end.toInt());
    }

    /**
     * Construct a range from start to end inclusively
     * @param start the start sort code
     * @param end the end sort code
     */
    public SortCodeRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Get the start sort code
     */
    public SortCode getStart() {
        return new SortCode(start);
    }

    /**
     * Get the end sort code
     */
    public SortCode getEnd() {
        return new SortCode(end);
    }

    /**
     * Check if the supplied sort code exists within the range
     * @param sortCode the sort code
     */
    public boolean contains(SortCode sortCode) {
        return contains(sortCode.toInt());
    }

    /**
     * Check if the supplied sort code exists within the range
     * @param sortCode the sort code as an integer value
     */
    public boolean contains(int sortCode) {
        return sortCode >= start && sortCode <= end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SortCodeRange that = (SortCodeRange) o;
        return start == that.start && end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return String.format("%s to %s", new SortCode(start), new SortCode(end));
    }
}
