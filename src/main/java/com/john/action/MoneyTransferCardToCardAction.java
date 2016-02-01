package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.User;
import com.john.validator.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class MoneyTransferCardToCardAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!isValidData(req)) {
            return "index";
        }
        StringBuilder cardNumberFrom = new StringBuilder();
        cardNumberFrom.append(req.getParameter("card_number_from_1"));
        cardNumberFrom.append(req.getParameter("card_number_from_2"));
        cardNumberFrom.append(req.getParameter("card_number_from_3"));
        cardNumberFrom.append(req.getParameter("card_number_from_4"));
        StringBuilder cardNumberTo = new StringBuilder();
        cardNumberTo.append(req.getParameter("card_number_to_1"));
        cardNumberTo.append(req.getParameter("card_number_to_2"));
        cardNumberTo.append(req.getParameter("card_number_to_3"));
        cardNumberTo.append(req.getParameter("card_number_to_4"));

        Double amount = Double.valueOf(req.getParameter("amount"));
        String currency = req.getParameter("currency");

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);
            daoFactory.setLockMode(1);

            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            PaymentCard paymentCardFrom = paymentCardDao.getByColumn("number", cardNumberFrom.toString());
            PaymentCard paymentCardTo = paymentCardDao.getByColumn("number", cardNumberTo.toString());

            if (paymentCardFrom == null || paymentCardTo == null) {
                req.setAttribute("transferError", "error.empty.payment.card");
                return "index";
            }
            if (paymentCardFrom.isBlocked()) {
                req.setAttribute("transferError", "text.content.card.from.blocked");
                return "index";
            }
            if (paymentCardTo.isBlocked()) {
                req.setAttribute("transferError", "text.content.card.to.blocked");
                return "index";
            }
            if (!isDataCoincideFrom(paymentCardFrom, req) && !isDataCoincideTo(paymentCardTo, req)) {
                return "index";
            }

            if (!paymentCardFrom.removeBalance(currency, amount)) {
                req.setAttribute("transferError", "error.500");
                return "index";
            }
            if (!paymentCardTo.refillBalance(currency, amount)) {
                req.setAttribute("transferError", "error.500");
                return "index";
            }

            if (paymentCardDao.update(paymentCardFrom) != 1) {
                return "index";
            }
            if (paymentCardDao.update(paymentCardTo) != 1) {
                return "index";
            }

            daoFactory.setAutoCommit(true);
        } catch (Exception e) {
            req.setAttribute("transferError", "error.500");
            throw new ActionException(e);
        }
        req.getSession().setAttribute("transfer", "text.content.transfer.successfully");
        return "redirect:DisplayPage&location=index";
    }

    private boolean isValidData(HttpServletRequest req) {
        System.out.println("start isValidData itd");
        boolean isValid = true;

        String[] fullNameFrom = req.getParameter("name_from").trim().split(" ");
        String[] fullNameTo = req.getParameter("name_from").split(" ");
        String nameFrom = null;
        String surnameFrom = null;
        String nameTo = null;
        String surnameTo = null;

        try {
            nameFrom = fullNameFrom[0];
            surnameFrom = fullNameFrom[1];
            nameTo = fullNameTo[0];
            surnameTo = fullNameTo[1];
        } catch (Exception e) {
        }

        StringBuilder cardNumberFrom = new StringBuilder();
        cardNumberFrom.append(req.getParameter("card_number_from_1"));
        cardNumberFrom.append(req.getParameter("card_number_from_2"));
        cardNumberFrom.append(req.getParameter("card_number_from_3"));
        cardNumberFrom.append(req.getParameter("card_number_from_4"));

        String cardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");

        StringBuilder cardNumberTo = new StringBuilder();
        cardNumberTo.append(req.getParameter("card_number_to_1"));
        cardNumberTo.append(req.getParameter("card_number_to_2"));
        cardNumberTo.append(req.getParameter("card_number_to_3"));
        cardNumberTo.append(req.getParameter("card_number_to_4"));

        String amount = req.getParameter("amount");

        if (!NameValidator.isValidName(nameFrom, req)) {
            isValid = false;
        } else {
            req.setAttribute("nameFrom", nameFrom);
        }
        if (!NameValidator.isValidSurname(surnameFrom, req)) {
            isValid = false;
        } else {
            req.setAttribute("surnameFrom", surnameFrom);
        }
        if (!CardNumberValidator.isValid(cardNumberFrom.toString(), req)) {
            isValid = false;
        } else {
            req.setAttribute("cardNumberFrom1", req.getParameter("card_number_from_1"));
            req.setAttribute("cardNumberFrom2", req.getParameter("card_number_from_2"));
            req.setAttribute("cardNumberFrom3", req.getParameter("card_number_from_3"));
            req.setAttribute("cardNumberFrom4", req.getParameter("card_number_from_4"));
        }
        if (!CardExpiryDateValidator.isValid(cardExpiryDate, req)) {
            isValid = false;
        } else {
            req.setAttribute("cardExpiryDate", cardExpiryDate);
        }
        if (!SecurityCodeValidator.isValid(securityCode, req)) {
            isValid = false;
        }
        if (!NameValidator.isValidName(nameTo, req)) {
            isValid = false;
        } else {
            req.setAttribute("nameTo", nameTo);
        }
        if (!NameValidator.isValidSurname(surnameTo, req)) {
            isValid = false;
        } else {
            req.setAttribute("surnameTo", surnameTo);
        }
        if (!CardNumberValidator.isValid(cardNumberTo.toString(), req)) {
            isValid = false;
        } else {
            req.setAttribute("cardNumberTo1", req.getParameter("card_number_to_1"));
            req.setAttribute("cardNumberTo2", req.getParameter("card_number_to_2"));
            req.setAttribute("cardNumberTo3", req.getParameter("card_number_to_3"));
            req.setAttribute("cardNumberTo4", req.getParameter("card_number_to_4"));
        }
        if (!AmountValidator.isValid(amount, req)) {
            isValid = false;
        } else {
            req.setAttribute("amount", amount);
        }

        return isValid;
    }

    private boolean isDataCoincideFrom(PaymentCard paymentCard, HttpServletRequest req) {
        boolean isCoincide = true;
        String[] fullName = req.getParameter("name_from").split(" ");
        String name = fullName[0];
        String surname = fullName[1];
        String strCardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");

        User user = paymentCard.getUser();
        if (!user.getName().equals(name) || !user.getSurname().equals(surname)) {
            req.setAttribute("transferError", "error.do.not.coincide.card.from");
            isCoincide = false;
        } else {
            req.setAttribute("nameFrom", name);
            req.setAttribute("surnameFrom", surname);
        }
        Date cardExpiryDate = PaymentCard.cardExpiryDateValueOf(strCardExpiryDate);
        if (!paymentCard.getCardExpiryDate().equals(cardExpiryDate)) {
            req.setAttribute("transferError", "error.do.not.coincide.card.from");
            isCoincide = false;
        } else {
            req.setAttribute("cardExpiryDate", strCardExpiryDate);
        }
        if (!(paymentCard.getSecurityCode() == Integer.valueOf(securityCode))) {
            req.setAttribute("transferError", "error.do.not.coincide.card.from");
            isCoincide = false;
        }
        return isCoincide;
    }

    private boolean isDataCoincideTo(PaymentCard paymentCard, HttpServletRequest req) {
        boolean isCoincide = true;
        String[] fullName = req.getParameter("name_from").split(" ");
        String name = fullName[0];
        String surname = fullName[1];

        User user = paymentCard.getUser();
        if (!user.getName().equals(name) || !user.getSurname().equals(surname)) {
            req.setAttribute("transferError", "error.do.not.coincide.card.to");
            isCoincide = false;
        } else {
            req.setAttribute("nameTo", name);
            req.setAttribute("surnameTo", surname);
        }

        return isCoincide;
    }
}
