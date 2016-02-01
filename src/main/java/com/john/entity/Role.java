package com.john.entity;

import com.john.dao.DaoException;
import com.john.dao.Identified;

import java.sql.ResultSet;
import java.sql.SQLException;

@BaseEntityDao
public class Role implements Identified<Integer> {
    private Integer id;
    private String name;
    private int sessionLifeTime;

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

    public int getSessionLifeTime() {
        return sessionLifeTime;
    }

    public void setSessionLifeTime(int sessionLifeTime) {
        this.sessionLifeTime = sessionLifeTime;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sessionLifeTime=" + sessionLifeTime +
                '}';
    }
}
