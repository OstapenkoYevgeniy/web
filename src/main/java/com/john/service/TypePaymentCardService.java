package com.john.service;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.TypePaymentCard;
import org.slf4j.Logger;

import java.util.List;

public class TypePaymentCardService {
    static Logger log = org.slf4j.LoggerFactory.getLogger(TypePaymentCardService.class);

    public static List<TypePaymentCard> getTypesPaymentCards() {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<TypePaymentCard, Integer> typePaymentCardDao = daoFactory.getDao(TypePaymentCard.class);
            List<TypePaymentCard> typesPaymentCards = typePaymentCardDao.getAll();
            if (typesPaymentCards == null) {
                throw new InternalServerError();
            }
            return typesPaymentCards;
        } catch (Exception e) {
            log.error("", e);
            throw new InternalServerError();
        }
    }
}
