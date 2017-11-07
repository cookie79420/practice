package com.dev.util;

public class TextUtils {

    public static boolean isBlank(String str) {
        return str == null || (str.trim().length() == 0) ? true : false;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
