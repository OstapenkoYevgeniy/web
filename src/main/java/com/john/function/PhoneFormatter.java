package com.john.function;

/**
 * Formats phone number according to a pattern.
 * <p>
 * For designation of number in a pattern use 'd' or 'D'.
 * <p>
 * Example:
 * - Phone: 7771112233
 * - Pattern: +7(ddd)ddd-dd-dd
 * - Result: +7(777)111-22-33
 */
public class PhoneFormatter {
    public static String format(String phone, String pattern) throws Exception {
        pattern = pattern.replaceAll("D", "d");
        int countPhoneDigit = phone.length();

        if (pattern.chars().filter(digit -> 'd' == digit).count() != countPhoneDigit) {
            throw new Exception("Incorrect pattern, it is necessary to enter 10 figures.");
        }

        StringBuilder result = new StringBuilder();
        int phoneDigit = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'd') {
                result.append(phone.charAt(phoneDigit));
                phoneDigit++;
            } else {
                result.append(pattern.charAt(i));
            }
        }
        return result.toString();
    }
}
