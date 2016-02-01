package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientTransferDisplay implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User user = (User) req.getSession().getAttribute("user");
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);

            List<PaymentCard> paymentCards =
                    paymentCardDao.getAllByColumn("user_id", String.valueOf(user.getId()));

            req.setAttribute("paymentCards", paymentCards);
        } catch (Exception e) {
            throw new ActionException(e);
        }

        return "user/transfer";
    }
}
