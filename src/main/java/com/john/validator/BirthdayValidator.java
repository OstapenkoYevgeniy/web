package com.john.validator;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class BirthdayValidator {
    public static boolean isValid(String birthday, HttpServletRequest req) {
        if (birthday == null || birthday.length() == 0) {
            req.setAttribute("birthdayError", "error.empty.birthday");
            return false;
        }
        try {
            Date.valueOf(birthday);
            return true;
        } catch (Exception e) {
            req.setAttribute("birthdayError", "error.format.date");
            return false;
        }
    }
}
