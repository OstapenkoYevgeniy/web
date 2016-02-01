package com.john.dao;

import com.john.property.AppPropertyManager;
import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Concrete DAO factory has to have the static getInstance method,
 * which accepts the StorageAccess interface as parameter.
 */
public abstract class AbstractDaoFactory {
    static Logger log = org.slf4j.LoggerFactory.getLogger(AbstractDaoFactory.class);

    public static DaoFactory getDaoFactory() throws DaoException {
        DaoFactory daoFactory = null;
        try {
            String pathToDaoFactory = AppPropertyManager.getInstance().getProperty("path.dao.factory");
            String pathToStorageAccess = AppPropertyManager.getInstance().getProperty("path.storage.access");

            Method getInstance = Class.forName(pathToStorageAccess).getMethod("getInstance");
            StorageAccess storageAccess = (StorageAccess) getInstance.invoke(StorageAccess.class);

            Constructor constructor = Class.forName(pathToDaoFactory).getConstructor(StorageAccess.class);
            daoFactory = (DaoFactory) constructor.newInstance(storageAccess);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
        return daoFactory;
    }
}
