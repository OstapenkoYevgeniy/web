package com.john.service;

import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import org.slf4j.Logger;

import java.util.List;

public class DaoService {
    static Logger log = org.slf4j.LoggerFactory.getLogger(DaoService.class);

    public static List getAll(Class entity, int limit, int offset) throws ServiceException {
        return getAll(limit, offset, entity);
    }

    public static List getAll(Class entity) throws ServiceException {
        return getAll(-1, -1, entity);
    }

    private static List getAll(int limit, int offset, Class entity) throws ServiceException {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao dao = daoFactory.getDao(entity);
            if (limit == -1) {
                return dao.getAll();
            } else {
                return dao.getAll(limit, offset);
            }
        } catch (Exception e) {
            log.error("", e);
            throw new ServiceException(e);
        }
    }

    public static long getCount(Class entity) throws ServiceException {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao paymentCardDao = daoFactory.getDao(entity);
            return paymentCardDao.getCount();
        } catch (Exception e) {
            log.error("", e);
            throw new ServiceException(e);
        }
    }

    public static <T> T getByPk(Class entity, String key) throws ServiceException {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao paymentCardDao = daoFactory.getDao(entity);
            return (T) paymentCardDao.getByPK(key);
        } catch (Exception e) {
            log.error("", e);
            throw new ServiceException(e);
        }
    }
}
