package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class SecurityCodeValidator {
    public static boolean isValid(String securityCode, HttpServletRequest req) {
        if (securityCode == null || securityCode.length() == 0) {
            req.setAttribute("securityCodeError", "error.empty.security.code");
            return false;
        }
        if (securityCode.length() != 3) {
            req.setAttribute("securityCodeError", "error.incorrect.security.code");
            return false;
        }
        try {
            Integer.valueOf(securityCode);
        } catch (Exception e) {
            req.setAttribute("securityCodeError", "error.incorrect.security.code");
            return false;
        }
        req.setAttribute("securityCode", securityCode);
        return true;
    }
}
