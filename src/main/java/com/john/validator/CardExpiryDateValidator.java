package com.john.validator;

import com.john.entity.PaymentCard;

import javax.servlet.http.HttpServletRequest;

public class CardExpiryDateValidator {
    public static boolean isValid(String cardExpiryDate, HttpServletRequest req) {
        if (cardExpiryDate == null || cardExpiryDate.trim().length() == 0) {
            req.setAttribute("cardExpiryDateError", "error.empty.card.expiry.date");
            return false;
        }
        try {
            PaymentCard.cardExpiryDateValueOf(cardExpiryDate);
        } catch (Exception e) {
            req.setAttribute("cardExpiryDateError", "error.incorrect.card.expiry.date");
            return false;
        }
        req.setAttribute("cardExpiryDate", cardExpiryDate);
        return true;
    }
}
