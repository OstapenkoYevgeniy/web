package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.validator.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class RefillBalanceAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!isValidData(req)) {
            return "manager/refill_balance";
        }

        String cardNumber = req.getParameter("card_number");
        String amountTransfer = req.getParameter("amount").replace(",", "."); // TODO если нулл все падает
        String currency = req.getParameter("currency");

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);
            daoFactory.setLockMode(1);

            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            PaymentCard paymentCard = paymentCardDao.getByColumn("number", cardNumber);

            if (paymentCard == null) {
                req.setAttribute("refillError", "error.card.not.found");
                return "manager/refill_balance"; // TODO Error
            }

            if (!isDataCoincideFrom(paymentCard, req)) {
                req.setAttribute("refillError", "error.no.coincide.data");
                return "manager/refill_balance";
            }

            if (!paymentCard.refillBalance(currency, Double.valueOf(amountTransfer))) {
                req.setAttribute("refillError", "error.500");
                return "manager/refill_balance"; // TODO Error
            }

            if (paymentCardDao.update(paymentCard) != 1) {
                req.setAttribute("refillError", "error.500");
                daoFactory.rollback();
            } else {
                daoFactory.setAutoCommit(true);
            }
        } catch (Exception e) {
            throw new ActionException(e);
        }
        return "redirect:DisplayPage&location=manager/refill_balance_successfully";
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValid = true;

        if (!IinValidator.isValid(req.getParameter("iin"), req)) {
            isValid = false;
        } else {
            req.setAttribute("iin", req.getParameter("iin"));
        }
        if (!NameValidator.isValidName(req.getParameter("name"), req)) {
            isValid = false;
        } else {
            req.setAttribute("name", req.getParameter("name"));
        }
        if (!NameValidator.isValidSurname(req.getParameter("surname"), req)) {
            isValid = false;
        } else {
            req.setAttribute("surname", req.getParameter("surname"));
        }
        if (!NameValidator.isValidPatronymic(req.getParameter("patronymic"), req)) {
            isValid = false;
        } else {
            req.setAttribute("patronymic", req.getParameter("patronymic"));
        }
        if (!CardNumberValidator.isValid(req.getParameter("card_number"), req)) {
            isValid = false;
        } else {
            req.setAttribute("cardNumber", req.getParameter("card_number"));
        }
        if (!CardExpiryDateValidator.isValid(req.getParameter("card_expiry_date"), req)) {
            isValid = false;
        } else {
            req.setAttribute("cardExpiryDate", req.getParameter("card_expiry_date"));
        }
        if (!AmountValidator.isValid(req.getParameter("amount"), req)) {
            isValid = false;
        } else {
            req.setAttribute("amount", req.getParameter("amount"));
        }

        return isValid;
    }

    private boolean isDataCoincideFrom(PaymentCard paymentCard, HttpServletRequest req) {
        boolean isCoincide = true;
        String iin = req.getParameter("iin");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String cardExpiryDate = req.getParameter("card_expiry_date");
        if (!Long.valueOf(iin).equals(paymentCard.getUser().getIin()))
            return false;
        if (!paymentCard.getUser().getName().equals(name))
            return false;
        if (!paymentCard.getUser().getSurname().equals(surname))
            return false;
        if (!paymentCard.getUser().getPatronymic().equals(patronymic))
            return false;
        Date tmpDate = PaymentCard.cardExpiryDateValueOf(cardExpiryDate);
        if (!paymentCard.getCardExpiryDate().equals(tmpDate))
            return false;
        return isCoincide;
    }
}
