package com.john.validator;

import javax.servlet.http.HttpServletRequest;

public class CardNumberValidator {
    public static boolean isValid(String cardNumber, HttpServletRequest req) {
        if (cardNumber == null || cardNumber.length() == 0) {
            req.setAttribute("cardNumberError", "error.empty.card.number");
            return false;
        }
        if (cardNumber.length() != 16) {
            req.setAttribute("cardNumberError", "error.incorrect.card.number");
            return false;
        }
        try {
            Long.valueOf(cardNumber);
        } catch (Exception e) {
            req.setAttribute("cardNumberError", "error.incorrect.card.number");
            return false;
        }
        req.setAttribute("cardNumber", cardNumber);
        return true;
    }
}
