package com.nls.bank;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Representation of the 14 digits that comprise the sort code and account number
 */
public class Digits {
    public static final int U = 0;
    public static final int V = 1;
    public static final int W = 2;
    public static final int X = 3;
    public static final int Y = 4;
    public static final int Z = 5;
    public static final int A = 6;
    public static final int B = 7;
    public static final int C = 8;
    public static final int D = 9;
    public static final int E = 10;
    public static final int F = 11;
    public static final int G = 12;
    public static final int H = 13;

    private final int[] digits;

    /**
     * Constructor
     * @param digits an array of 14 integers
     */
    public Digits(int[] digits) {
        if (digits.length != 14) {
            throw new IllegalArgumentException("Digit array must be exactly 14 digits long");
        }
        this.digits = digits;
    }

    /**
     * Get the underlying array of digits
     * @return the digits
     */
    public int[] get() {
        return digits;
    }

    /**
     * Get the digit at a specific location
     * @param i the location, 0 based
     * @return the digit at the specified location
     */
    public int get(int i) {
        return digits[i];
    }

    public int u() {
        return digits[U];
    }

    public int v() {
        return digits[V];
    }

    public int w() {
        return digits[W];
    }

    public int x() {
        return digits[X];
    }

    public int y() {
        return digits[Y];
    }

    public int z() {
        return digits[Z];
    }

    public int a() {
        return digits[A];
    }

    public int b() {
        return digits[B];
    }

    public int c() {
        return digits[C];
    }

    public int d() {
        return digits[D];
    }

    public int e() {
        return digits[E];
    }

    public int f() {
        return digits[F];
    }

    public int g() {
        return digits[G];
    }

    public int h() {
        return digits[H];
    }

    /**
     * Create a new instance of digits with the values within the specified range set to 0
     * @param start the start index (inclusive)
     * @param end the end index (inclusive)
     * @return a new Digits instance with the specified range digits transformed to 0
     */
    public Digits zeroise(int start, int end) {
        int[] copy = Arrays.copyOf(digits, digits.length);
        start = Math.max(start, 0);
        end = Math.min(end, digits.length - 1);
        for (int i = start; i <= end; ++i) {
            copy[i] = 0;
        }
        return new Digits(copy);
    }

    /**
     * Create a new instance of digits with the values within the specified range shifted by the value
     * of shift and the spaces left padded by the value of pad.
     * @param start the start index (inclusive)
     * @param end the end index (inclusive)
     * @param shift the number of spaces to shift the numbers right, a negative number will shift
     *              the number left and pad to the right
     * @param pad the digit to pad in the spaces left
     * @return a new Digits instance with the specified range digits transformed to 0
     */
    public Digits shift(int start, int end, int shift, int pad) {
        int[] copy = Arrays.copyOf(digits, digits.length);
        start = Math.max(start, 0);
        end = Math.min(end, digits.length - 1);

        if (shift < 0) {
            int i;
            for (i = start; i <= end + shift; ++i) {
                copy[i] = copy[i - shift];
            }

            for (; i <= end; ++i) {
                copy[i] = pad;
            }
        } else {
            int i;
            for (i = end; i >= start + shift; --i) {
                copy[i] = copy[i - shift];
            }

            for (; i >= start; --i) {
                copy[i] = pad;
            }
        }
        return new Digits(copy);
    }

    /**
     * Convert a string of digits to an IntStream
     * @param n a string of digits
     * @return an IntStream of the individual digits
     */
    public static IntStream toDigits(String n) {
        return n.chars().map(c -> c - '0');
    }

    /**
     * Convert an integer to a stream of it's digits
     * @param n an integer
     * @return an IntStream of the individual digits
     */
    public static IntStream toDigits(int n) {
        return toDigits(Integer.toString(n));
    }

    @Override
    public String toString() {
        return Arrays.toString(digits);
    }
}
