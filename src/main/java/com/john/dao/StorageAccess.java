package com.john.dao;

/**
 * Interface of connection to storage of data.
 *
 * @param <T> Type of access to storage of data.
 */
public interface StorageAccess<T> {
    T getConnection() throws StorageAccessException;

    void returnConnection(T typeAccess) throws StorageAccessException;
}
