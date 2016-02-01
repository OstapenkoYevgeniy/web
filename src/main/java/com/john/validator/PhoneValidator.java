package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class PhoneValidator {
    public static boolean isValid(String phone, HttpServletRequest req) { // TODO
        if (phone == null || phone.trim().length() == 0) {
            req.setAttribute("phoneError", "error.empty.phone");
            return false;
        }
        if (phone.length() != 10) {
            req.setAttribute("phoneError", "error.incorrect.phone");
            return false;
        }
        try {
            Long.parseLong(phone);
        } catch (NumberFormatException nfe) {
            req.setAttribute("phoneError", "error.incorrect.phone");
            return false;
        }

        req.setAttribute("phone", phone);
        return true;
    }
}
