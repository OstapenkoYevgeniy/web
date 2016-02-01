package com.john.action;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.User;
import com.john.validator.CardNumberValidator;
import com.john.validator.IinValidator;
import com.john.validator.NameValidator;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BlockCardAction implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(BlockCardAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String iin = req.getParameter("iin");
        String cardNumber = req.getParameter("card_number");
        String block = req.getParameter("block");

        if (!isValidData(req)) {
            return getView(req);
        }

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<User, Long> userDao = daoFactory.getDao(User.class);
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);

            User user = userDao.getByColumn("iin", iin);
            if (user == null) {
                req.setAttribute("blockError", "error.user.not.found");
                return getView(req);
            }
            if (!isDataCoincide(user, req)) {
                req.setAttribute("blockError", "error.user.data.do.not.coincide");
                return getView(req);
            }

            if (cardNumber.trim().isEmpty()) {
                List<PaymentCard> paymentCards = paymentCardDao.getAllByColumn("user", String.valueOf(user.getId()));
                if (paymentCards == null) {
                    req.setAttribute("blockError", "error.payment.card.empty");
                    return getView(req);
                }
                for (PaymentCard paymentCard : paymentCards) {
                    paymentCard.setBlocked(Boolean.valueOf(block));
                    paymentCardDao.update(paymentCard);
                }
            } else {
                PaymentCard paymentCard = paymentCardDao.getByColumn("number", cardNumber);
                if (paymentCard == null) {
                    req.setAttribute("blockError", "error.payment.card.empty");
                    return getView(req);
                }
                paymentCard.setBlocked(Boolean.valueOf(block));
                paymentCardDao.update(paymentCard);
            }
        } catch (Exception e) {
            log.error("", e);
            throw new InternalServerError(e);
        }
        return "redirect:DisplayPage&location=manager/block_card_successfully";
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValidData = true;
        if (!IinValidator.isValid(req.getParameter("iin"), req))
            isValidData = false;
        if (!NameValidator.isValidName(req.getParameter("name"), req))
            isValidData = false;
        if (!NameValidator.isValidSurname(req.getParameter("surname"), req))
            isValidData = false;
        if (!NameValidator.isValidPatronymic(req.getParameter("patronymic"), req))
            isValidData = false;
        String cardNumber = req.getParameter("card_number");
        if (cardNumber != null && cardNumber.trim().length() != 0) {
            if (!CardNumberValidator.isValid(cardNumber, req)) {
                isValidData = false;
            }
        }
        return isValidData;
    }

    private boolean isDataCoincide(User user, HttpServletRequest req) {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        boolean isDataCoincide = true;
        if (!user.getName().equals(name))
            isDataCoincide = false;
        if (!user.getSurname().equals(surname))
            isDataCoincide = false;
        if (!user.getPatronymic().equals(patronymic))
            isDataCoincide = false;
        return isDataCoincide;
    }

    private String getView(HttpServletRequest req) {
        if (req.getParameter("block").equals("true")) {
            return "manager/block_card";
        } else {
            return "manager/unblock_card";
        }
    }
}
