package com.john.validator;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator {
    public static boolean isValidName(String name, HttpServletRequest req) {
        return isValid(name, "name", req);
    }

    public static boolean isValidSurname(String surname, HttpServletRequest req) {
        return isValid(surname, "surname", req);
    }

    public static boolean isValidPatronymic(String patronymic, HttpServletRequest req) {
        return isValid(patronymic, "patronymic", req);
    }

    private static boolean isValid(String string, String value, HttpServletRequest req) {
        if (string == null || string.length() == 0) {
            req.setAttribute(value + "Error", "error.empty." + value);
            return false;
        }
        Pattern p = Pattern.compile("[\\p{Alpha}|а-яА-ЯёЁ]{2,50}");
        Matcher m = p.matcher(string);
        if (!m.matches()) {
            req.setAttribute(value + "Error", "error.incorrect." + value);
            return false;
        }
        req.setAttribute(value, string);
        return true;
    }
}
