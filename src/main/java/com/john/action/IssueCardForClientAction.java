package com.john.action;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.IssuePaymentCard;
import com.john.entity.PaymentCard;
import com.john.entity.User;
import com.john.service.DaoService;
import com.john.service.SecurityService;
import com.john.service.ServiceException;
import com.john.validator.CardExpiryDateValidator;
import com.john.validator.CardNumberValidator;
import com.john.validator.CodewordValidator;
import com.john.validator.SecurityCodeValidator;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueCardForClientAction implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(IssueCardForClientAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!SecurityService.access(req, "Manager")) {
            return "index";
        }
        String issuePaymentCardId = req.getParameter("issue_payment_card_id");
        if (!isValidData(req)) {
            try {
                req.setAttribute("issuePaymentCard", DaoService.getByPk(IssuePaymentCard.class, issuePaymentCardId));
            } catch (ServiceException e) {
                log.error("", e);
                throw new InternalServerError(e);
            }
            return "manager/issue_card_for_client";
        }

        String cardNumber = req.getParameter("card_number");
        String codeword = req.getParameter("codeword");
        String cardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");

        User client = (User) req.getSession(false).getAttribute("client");
        PaymentCard paymentCard = new PaymentCard();
        IssuePaymentCard issuePaymentCard = null;

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);

            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            Dao<IssuePaymentCard, Long> issuePaymentCardDao = daoFactory.getDao(IssuePaymentCard.class);

            issuePaymentCard = issuePaymentCardDao.getByPK(Long.valueOf(issuePaymentCardId));

            paymentCard.setUser(client);
            paymentCard.setNumber(Long.valueOf(cardNumber));
            paymentCard.setCodeword(codeword);
            paymentCard.setTypePaymentCard(issuePaymentCard.getTypePaymentCard());
            paymentCard.setCardExpiryDate(PaymentCard.cardExpiryDateValueOf(cardExpiryDate));
            paymentCard.setSecurityCode(Short.valueOf(securityCode));
            paymentCardDao.insert(paymentCard);

            if (issuePaymentCardDao.delete(issuePaymentCard) > 1) {
                req.setAttribute("issueError", "error.500");
                daoFactory.rollback();
            } else {
                daoFactory.setAutoCommit(true);
            }
        } catch (Exception e) {
            log.error("", e);
            req.setAttribute("issuePaymentCard", issuePaymentCard);
            req.setAttribute("issueError", "error.500");
            return "manager/issue_card_for_client";
        }
        req.getSession(false).setAttribute("paymentCard", paymentCard);
        return "redirect:DisplayPage&location=manager/issue_card_successfully";
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValid = true;
        String cardNumber = req.getParameter("card_number");
        String codeword = req.getParameter("codeword");
        String cardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");
        if (!CardNumberValidator.isValid(cardNumber, req))
            isValid = false;
        if (!CodewordValidator.isValid(codeword, req))
            isValid = false;
        if (!CardExpiryDateValidator.isValid(cardExpiryDate, req))
            isValid = false;
        if (!SecurityCodeValidator.isValid(securityCode, req))
            isValid = false;
        return isValid;
    }
}

