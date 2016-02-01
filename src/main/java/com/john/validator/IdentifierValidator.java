package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class IdentifierValidator {
    public static boolean isValid(String identifier, HttpServletRequest req) {
        if (identifier == null || identifier.length() == 0) {
            req.setAttribute("identifierError", "error.empty.identifier");
            return false;
        }
        if (identifier.length() != 12) {
            req.setAttribute("identifierError", "error.incorrect.identifier");
            return false;
        }
        try {
            Long.valueOf(identifier);
        } catch (Exception e) {
            req.setAttribute("identifierError", "error.incorrect.identifier");
            return false;
        }
        req.setAttribute("identifier", identifier);
        return true;
    }
}
