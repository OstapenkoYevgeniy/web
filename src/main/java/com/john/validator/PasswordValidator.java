package com.john.validator;

import javax.servlet.http.HttpServletRequest;

// TODO РЕКВСТЫ

public class PasswordValidator {
    public static boolean isValid(String password, HttpServletRequest req) {
        if (password == null || password.trim().length() == 0) {
            req.setAttribute("passwordError", "error.empty.password");
            return false;
        }
        if (password.length() < 8 || password.length() > 20) {
            req.setAttribute("passwordError", "error.wrong.password");
            return false;
        }
        return true;
    }
}
