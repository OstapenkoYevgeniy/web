package com.john.entity;

import com.john.dao.DaoException;
import com.john.dao.Identified;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@BaseEntityDao
public class User implements Identified<Long> {
    private Long id;
    private long identifier;
    private long iin;
    private String password;
    private String name;
    private String surname;
    private String patronymic;
    private Role role;
    private Date birthday;
    private boolean active; // "true" if the user is registered.

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(ResultSet generatedKey) throws DaoException {
        try {
            id = generatedKey.getLong(1);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public long getIin() {
        return iin;
    }

    public void setIin(long iin) {
        this.iin = iin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", identifier=" + identifier +
                ", iin=" + iin +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                ", birthday=" + birthday +
                ", active=" + active +
                '}';
    }
}
