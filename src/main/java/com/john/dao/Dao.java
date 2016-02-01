package com.john.dao;

import java.util.List;

/**
 * The interface for management of a condition of object in a database.
 *
 * @param <T> Object type.
 * @param <K> Type of primary key.
 */
public interface Dao<T, K> {
    T insert(T object) throws DaoException;

    int update(T object) throws DaoException;

    int delete(T object) throws DaoException;

    T getByPK(K key) throws DaoException;

    T getByColumn(String column, String value) throws DaoException;

    List<T> getAllByColumn(String column, String value) throws DaoException;

    List<T> getAll() throws DaoException;

    List<T> getAll(int limit, int offset) throws DaoException;

    long getCount() throws DaoException;
}
