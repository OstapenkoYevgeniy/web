package com.john.dao;

import java.sql.ResultSet;

/**
 * The Interface of the identified objects.
 *
 * @param <K> Type of primary key.
 */
public interface Identified<K> {
    K getId();

    void setId(ResultSet generatedKey) throws DaoException;
}
