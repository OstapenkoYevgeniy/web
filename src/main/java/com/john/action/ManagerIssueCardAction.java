package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.IssuePaymentCard;
import com.john.entity.User;
import com.john.service.SecurityService;
import com.john.validator.IinValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerIssueCardAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        if (!SecurityService.access(req, "Manager"))
            return "index";
        if (!isValidData(req))
            return "manager/issue_card";

        String iin = req.getParameter("iin");

        IssuePaymentCard issuePaymentCard;
        User user;
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<IssuePaymentCard, Long> issuePaymentCardDao = daoFactory.getDao(IssuePaymentCard.class);
            Dao<User, Long> userDao = daoFactory.getDao(User.class);

            issuePaymentCard = issuePaymentCardDao.getByColumn("iin", iin);
            user = userDao.getByColumn("iin", iin);
        } catch (Exception e) {
            throw new ActionException(e);
        }
        if (issuePaymentCard == null) {
            req.setAttribute("issueError", "error.empty.request.issue.payment.card");
            return "manager/issue_card";
        }
        if (user == null) {
            req.setAttribute("issuePaymentCard", issuePaymentCard);
            return "manager/issue_card_for_new_client";
        } else {
            req.setAttribute("issuePaymentCard", issuePaymentCard);
            req.getSession(false).setAttribute("client", user);
            return "manager/issue_card_for_client";
        }
    }

    private boolean isValidData(HttpServletRequest req) {
        return IinValidator.isValid(req.getParameter("iin"), req);
    }
}