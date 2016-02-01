package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class AmountValidator {
    public static boolean isValid(String amount, HttpServletRequest req) {
        if (amount == null || amount.trim().length() == 0) {
            req.setAttribute("amountError", "error.empty.amount");
            return false;
        }
        try {
            double sum = Double.valueOf(amount);
            if (sum < 0 || sum > 150000) {
                req.setAttribute("amountError", "error.incorrect.amount");
                return false;
            }
        } catch (Exception e) {
            req.setAttribute("amountError", "error.incorrect.amount");
            return false;
        }
        return true;
    }
}
