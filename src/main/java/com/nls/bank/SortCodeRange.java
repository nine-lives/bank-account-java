package com.nls.bank;

import java.util.Objects;

public class SortCodeRange {
    private final int start;
    private final int end;


    public SortCodeRange(String start, String end) {
        this(new SortCode(start), new SortCode(end));
    }

    public SortCodeRange(SortCode start, SortCode end) {
        this(start.toInt(), end.toInt());
    }

    public SortCodeRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public SortCode getStart() {
        return new SortCode(start);
    }

    public SortCode getEnd() {
        return new SortCode(end);
    }

    public boolean contains(SortCode sortCode) {
        return contains(sortCode.toInt());
    }

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
