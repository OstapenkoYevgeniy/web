package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientMoneyTransferAction implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            daoFactory.setAutoCommit(false);
            daoFactory.setLockMode(1);
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);

            Long cardFromId = Long.valueOf(req.getParameter("card_from"));
            Long cardToId = Long.valueOf(req.getParameter("card_to"));
            Double amount = Double.valueOf(req.getParameter("amount"));
            String currency = req.getParameter("currency");

            PaymentCard paymentCardFrom = paymentCardDao.getByPK(cardFromId);
            PaymentCard paymentCardTo = paymentCardDao.getByPK(cardToId);

            if (paymentCardFrom.isBlocked() || paymentCardTo.isBlocked()) {
                // TODO req
                return "user/transfer";
            }

            if (!paymentCardFrom.removeBalance(currency, amount)) {
                // TODO req
                return "index";
            }
            if (!paymentCardTo.refillBalance(currency, amount)) {
                // TODO req
                return "index";
            }

            paymentCardDao.update(paymentCardFrom);
            paymentCardDao.update(paymentCardTo);

            daoFactory.setAutoCommit(true);
            // TODO  аттрибуты добавить инфу о том что все гуд!
            return "redirect:ClientHomeDisplay";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException(e);
        }
    }
}
