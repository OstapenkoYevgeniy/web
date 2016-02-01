package com.john.action;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.IssuePaymentCard;
import com.john.entity.TypePaymentCard;
import com.john.service.DaoService;
import com.john.service.DataProcessing;
import com.john.service.ServiceException;
import com.john.validator.IinValidator;
import com.john.validator.NameValidator;
import com.john.validator.PhoneValidator;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Accepts data from a form.
 * Checks data for a correctness.
 * Writes down data using DAO.
 */
public class IssueCardAction implements Action {
    Logger log = org.slf4j.LoggerFactory.getLogger(IssueCardAction.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        String name = DataProcessing.process(req.getParameter("name"));
        String iin = DataProcessing.process(req.getParameter("iin"));
        String phone = DataProcessing.process(req.getParameter("phone"));
        String cardType = req.getParameter("card_type");

        log.debug("name = " + name);
        log.debug("iin = " + iin);
        log.debug("phone = " + phone);
        log.debug("cardType = " + cardType);

        boolean isValidData = true;

        if (!NameValidator.isValidName(name, req)) {
            isValidData = false;
        }

        if (!IinValidator.isValid(iin, req)) {
            isValidData = false;
        }

        if (!PhoneValidator.isValid(phone, req)) {
            isValidData = false;
        }

        if (!isValidData) {
            req.setAttribute("cardType", cardType);
            try {
                req.setAttribute("typesPaymentCards", DaoService.getAll(TypePaymentCard.class));
            } catch (ServiceException e) {
                throw new InternalServerError(e);
            }
            return "issue_card";
        }

        IssuePaymentCard issuePaymentCard = new IssuePaymentCard();

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<IssuePaymentCard, Long> issuePaymentCardDao = daoFactory.getDao(IssuePaymentCard.class);
            Dao<TypePaymentCard, Integer> typePaymentCardDao = daoFactory.getDao(TypePaymentCard.class);

            issuePaymentCard.setIin(Long.valueOf(iin));
            issuePaymentCard.setPhone(Long.valueOf(phone));
            issuePaymentCard.setClientName(name);

            TypePaymentCard typePaymentCard = typePaymentCardDao.getByPK(Integer.valueOf(cardType));
            issuePaymentCard.setTypePaymentCard(typePaymentCard);

            issuePaymentCard = issuePaymentCardDao.insert(issuePaymentCard);
        } catch (Exception e) {
            throw new ActionException(e);
        }

        req.getSession().setAttribute("issuePaymentCard", issuePaymentCard);

        return "redirect:DisplayPage&location=issue_payment_card_successfully";
    }
}
