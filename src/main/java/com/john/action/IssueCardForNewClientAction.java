package com.john.action;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.IssuePaymentCard;
import com.john.entity.PaymentCard;
import com.john.entity.Role;
import com.john.entity.User;
import com.john.service.DaoService;
import com.john.service.IdentifierGenerator;
import com.john.service.SecurityService;
import com.john.service.ServiceException;
import com.john.validator.*;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class IssueCardForNewClientAction implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(IssueCardForNewClientAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!SecurityService.access(req, "Manager")) {
            return "index";
        }
        String issuePaymentCardId = req.getParameter("issue_payment_card_id");
        String iin = req.getParameter("iin");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String cardNumber = req.getParameter("card_number");
        String codeword = req.getParameter("codeword");
        String cardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");
        if (!isValidData(req)) {
            try {
                req.setAttribute("issuePaymentCard", DaoService.getByPk(IssuePaymentCard.class, issuePaymentCardId));
            } catch (ServiceException e) {
                log.error("", e);
                throw new InternalServerError(e);
            }
            return "manager/issue_card_for_new_client";
        }

        User user = new User();
        PaymentCard paymentCard = new PaymentCard();
        IssuePaymentCard issuePaymentCard = null;

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);

            Dao<User, Long> userDao = daoFactory.getDao(User.class);
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            Dao<Role, Integer> roleDao = daoFactory.getDao(Role.class);
            Dao<IssuePaymentCard, Long> issuePaymentCardDao = daoFactory.getDao(IssuePaymentCard.class);

            issuePaymentCard = issuePaymentCardDao.getByPK(Long.valueOf(issuePaymentCardId));

            user.setRole(roleDao.getByColumn("name", "Client"));
            user.setIdentifier(IdentifierGenerator.generate());
            user.setIin(Long.valueOf(iin));
            user.setName(name);
            user.setSurname(surname);
            user.setPatronymic(patronymic);
            user.setBirthday(Date.valueOf(year + "-" + month + "-" + day));
            user = userDao.insert(user);

            paymentCard.setUser(user);
            paymentCard.setNumber(Long.valueOf(cardNumber));
            paymentCard.setTypePaymentCard(issuePaymentCard.getTypePaymentCard());
            paymentCard.setCodeword(codeword);
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
            return "manager/issue_card_for_new_client";
        }
        req.getSession(false).setAttribute("paymentCard", paymentCard);
        return "redirect:DisplayPage&location=manager/issue_card_successfully";
    }

    private boolean isValidData(HttpServletRequest req) {
        boolean isValid = true;
        String iin = req.getParameter("iin");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String patronymic = req.getParameter("patronymic");
        String day = req.getParameter("day");
        String month = req.getParameter("month");
        String year = req.getParameter("year");
        String cardNumber = req.getParameter("card_number");
        String codeword = req.getParameter("codeword");
        String cardExpiryDate = req.getParameter("card_expiry_date");
        String securityCode = req.getParameter("security_code");

        if (!IinValidator.isValid(iin, req))
            isValid = false;
        if (!NameValidator.isValidName(name, req))
            isValid = false;
        if (!NameValidator.isValidSurname(surname, req))
            isValid = false;
        if (!NameValidator.isValidPatronymic(patronymic, req))
            isValid = false;
        if (BirthdayValidator.isValid(year + "-" + month + "-" + day, req)) {
            req.setAttribute("returnDay", day);
            req.setAttribute("returnMonth", month);
            req.setAttribute("returnYear", year);
        } else {
            isValid = false;
        }
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

