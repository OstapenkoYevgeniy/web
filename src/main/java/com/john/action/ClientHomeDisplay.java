package com.john.action;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ClientHomeDisplay implements Action {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
        User user = (User) req.getSession().getAttribute("user"); // TODO проверки всякие

        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<PaymentCard, Long> paymentCardDao = daoFactory.getDao(PaymentCard.class);
            List<PaymentCard> paymentCards
                    = paymentCardDao.getAllByColumn("user", String.valueOf(user.getId()));
            req.setAttribute("paymentCards", paymentCards);
            return "client/home";
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionException(e);
        }
    }
}
