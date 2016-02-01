package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class CodewordValidator {
    public static boolean isValid(String codeword, HttpServletRequest req) {
        if (codeword == null || codeword.trim().length() == 0) {
            req.setAttribute("codewordError", "error.empty.codeword");
            return false;
        }
        if (codeword.length() < 4 || codeword.length() > 10) {
            req.setAttribute("codewordError", "error.wrong.codeword");
            return false;
        }
        if (codeword.indexOf(" ") != -1) {
            req.setAttribute("codewordError", "error.wrong.codeword");
            return false;
        }

        req.setAttribute("codeword", codeword);
        return true;
    }
}
