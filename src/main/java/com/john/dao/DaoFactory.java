package com.john.dao;

public interface DaoFactory extends AutoCloseable {
    Dao getDao(Class clazz) throws DaoException;

    void init() throws DaoException;

    void returnContext() throws DaoException;

    void setAutoCommit(boolean autoCommit) throws DaoException;

    void rollback() throws DaoException;

    void setLockMode(int lockMode) throws DaoException;
}
