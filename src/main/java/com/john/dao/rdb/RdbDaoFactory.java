package com.john.dao.rdb;

import com.john.dao.Dao;
import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.dao.StorageAccess;
import com.john.dbcp.ConnectionPool;
import com.john.entity.BaseEntityDao;
import com.john.property.AppPropertyManager;
import org.reflections.Reflections;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RdbDaoFactory implements DaoFactory {
    Logger log = org.slf4j.LoggerFactory.getLogger(RdbDaoFactory.class);
    private static Map<Class, Class> daoFactories;
    private ConnectionPool connectionPool;
    private Connection connection;
    private boolean autoCommit = true;
    private int lockMode = 3; // Default

    public RdbDaoFactory(StorageAccess storageAccess) throws DaoException {
        connectionPool = (ConnectionPool) storageAccess;
        try {
            connection = connectionPool.getConnection();
            connection.setAutoCommit(autoCommit);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Dao getDao(Class entity) throws DaoException {
        if (daoFactories == null) {
            init();
        }
        Class daoClass = daoFactories.get(entity);
        if (daoClass == null) {
            log.error("DAO isn't found!");
            throw new DaoException("DAO isn't found!");
        }
        try {
            return (Dao) daoClass.getConstructor(Connection.class, DaoFactory.class).newInstance(connection, this);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void init() throws DaoException {
        synchronized (this) {
            daoFactories = new HashMap<>();
            String packageDaoEntity;
            try {
                packageDaoEntity = AppPropertyManager.getInstance().getProperty("path.package.entities");
            } catch (Exception e) {
                throw new DaoException(e);
            }

            Reflections reflections = new Reflections(packageDaoEntity);
            Set<Class<?>> classesEntity = reflections.getTypesAnnotatedWith(BaseEntityDao.class);

            String thisClassName; // For receiving a prefix of a class "*DaoFactory"
            String packageName;
            String entityName;

            for (Class<?> entityClass : classesEntity) {
                thisClassName = this.getClass().getName();
                entityName = entityClass.getSimpleName();

                packageName = thisClassName.substring(0, thisClassName.lastIndexOf(".") + 1);
                packageName = packageName + entityName + this.getClass().getSimpleName().replace("Factory", "");

                try {
                    daoFactories.put(entityClass, Class.forName(packageName));
                } catch (Exception e) {
                    log.error("", e);
                    throw new DaoException(e);
                }
            }
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws DaoException {
        try {
            connection.setAutoCommit(this.autoCommit = autoCommit);
        } catch (SQLException e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void setLockMode(int lockMode) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement("SET LOCK_MODE " + lockMode)) {
            this.lockMode = lockMode;
            statement.execute();
        } catch (SQLException e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void returnContext() throws DaoException {
        try {
            connectionPool.returnConnection(connection);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        if (!autoCommit) {
            rollback();
        }
        if (lockMode != 3) {
            setLockMode(3);
        }
        returnContext();
        connection = null;
    }
}
