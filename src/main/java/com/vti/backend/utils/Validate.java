package com.vti.backend.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^[0-9]{10}$"
    );

    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    // Phương thức kiểm tra số điện thoại 10 số
    public static boolean isValidPhone(String phone) {
        Matcher matcher = PHONE_PATTERN.matcher(phone);
        return matcher.matches();
    }
}
