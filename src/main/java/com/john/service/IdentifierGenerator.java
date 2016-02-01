package com.john.service;

import com.john.InternalServerError;
import com.john.dao.AbstractDaoFactory;
import com.john.dao.Dao;
import com.john.dao.DaoFactory;
import com.john.entity.User;
import org.slf4j.Logger;

import java.util.Random;

public class IdentifierGenerator {
    static Logger log = org.slf4j.LoggerFactory.getLogger(PasswordService.class);

    public static long generate() {
        try (DaoFactory daoFactory = AbstractDaoFactory.getDaoFactory()) {
            Dao<User, Long> userDao = daoFactory.getDao(User.class);
            User user;
            boolean ifExists = true;
            long result = 0;

            while (ifExists) {
                result = generateIdentifier();
                try {
                    user = userDao.getByColumn("identifier", String.valueOf(result));
                } catch (Exception e) {
                    log.error("", e);
                    throw new InternalServerError();
                }
                if (user == null) {
                    ifExists = false;
                }
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new InternalServerError();
        }
    }

    private static long generateIdentifier() {
        Random r = new Random();
        return 100_000_000_000L + (long) (r.nextDouble() * (999_999_999_999L - 100_000_000_000L));
    }
}
