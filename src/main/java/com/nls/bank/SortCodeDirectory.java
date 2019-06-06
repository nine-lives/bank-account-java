package com.nls.bank;

public final class SortCodeDirectory {

    private SortCodeDirectory() {

    }

    public static boolean isNatwest(SortCode sortCode) {
        int code2 = code2(sortCode);
        int code4 = code4(sortCode);
        return code2 == 1 || (code4 >= 5000 && code4 <= 6699);
    }

    public static boolean isCoop(SortCode sortCode) {
        int code2 = code2(sortCode);
        int code4 = code4(sortCode);
        return code2 == 8 && !(code4 >= 830 && code4 <= 839);
    }

    public static boolean isSantander(SortCode sortCode) {
        int code2 = code2(sortCode);
        int code4 = code4(sortCode);
        return (code4 >= 900 && code4 <= 919)
                || code2 == 72
                || (code4 >= 8900 && code4 <= 8929);
    }

    private static int code4(SortCode sortCode) {
        return sortCode.toInt() / 100;
    }

    private static int code2(SortCode sortCode) {
        return sortCode.toInt() / 10000;
    }
}
