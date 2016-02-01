package com.john.entity;

import com.john.dao.DaoException;
import com.john.dao.Identified;

import java.sql.ResultSet;
import java.sql.SQLException;

@BaseEntityDao
public class TypePaymentCard implements Identified<Integer> {
    private Integer id;
    private String name;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(ResultSet generatedKey) throws DaoException {
        try {
            id = generatedKey.getInt(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TypePaymentCard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
