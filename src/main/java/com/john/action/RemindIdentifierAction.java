package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.User;
import com.john.service.PasswordService;
import com.john.validator.CardExpiryDateValidator;
import com.john.validator.CardNumberValidator;
import com.john.validator.CodewordValidator;
import com.john.validator.IinValidator;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class RemindIdentifierAction implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(RemindIdentifierAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!isValidData(req)) {
            return "restoration_password_or_identifier";
        }
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            PaymentCard paymentCard = paymentCardDao.getByColumn("number", req.getParameter("card_number"));
            if (!isDataCoincide(paymentCard, req)) {
                req.setAttribute("remindError", "error.no.coincide.data");
                return "restoration_password_or_identifier";
            }
            User user = paymentCard.getUser();
            if (req.getParameter("remind_identifier") != null) {
                req.getSession().setAttribute("remindUser", user);
                return "redirect:DisplayPage&location=restoration_successfully";
            } else {
                if (!user.isActive()) {
                    req.setAttribute("remindError", "error.do.not.registration");
                    return "restoration_password_or_identifier";
                }
                String password = req.getParameter("password");
                String retryPassword = req.getParameter("retry_password");
                if (!password.equals(retryPassword)) {
                    req.setAttribute("remindError", "error.registration.password.do.not.coincide");
                    return "restoration_password_or_identifier";
                }
                Dao<User, Long> userDao = daoFactory.getDao(User.class);
                user.setPassword(PasswordService.getSha2Password(password, user.getId()));
                if (userDao.update(user) != 1) {
                    req.setAttribute("remindError", "error.500");
                    return "restoration_password_or_identifier";
                }
                req.getSession().setAttribute("changePassword", user);
                return "redirect:DisplayPage&location=restoration_successfully";
            }
        } catch (Exception e) {
            log.debug("", e);
            throw new ActionException(e);
        }
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValidData = true;
        if (!CardNumberValidator.isValid(req.getParameter("card_number"), req))
            isValidData = false;
        if (!CodewordValidator.isValid(req.getParameter("codeword"), req))
            isValidData = false;
        if (!CardExpiryDateValidator.isValid(req.getParameter("card_expiry_date"), req))
            isValidData = false;
        if (!IinValidator.isValid(req.getParameter("iin"), req))
            isValidData = false;
        return isValidData;
    }

    private boolean isDataCoincide(PaymentCard paymentCard, HttpServletRequest req) {
        boolean isDataCoincide = true;
        if (paymentCard == null) {
            return false;
        }
        User user = paymentCard.getUser();
        Date tmpExpiryDate = PaymentCard.cardExpiryDateValueOf(req.getParameter("card_expiry_date"));
        if (!paymentCard.getCardExpiryDate().equals(tmpExpiryDate))
            isDataCoincide = false;
        if (!paymentCard.getCodeword().equals(req.getParameter("codeword")))
            isDataCoincide = false;
        if (!(user.getIin() == Long.valueOf(req.getParameter("iin"))))
            isDataCoincide = false;
        return isDataCoincide;
    }
}
