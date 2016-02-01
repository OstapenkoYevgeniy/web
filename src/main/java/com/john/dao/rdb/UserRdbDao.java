package com.john.dao.rdb;

import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.entity.Role;
import com.john.entity.User;
import com.john.property.SqlQueryPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserRdbDao extends AbstractRdbDao<User, Integer> {
    Logger log = org.slf4j.LoggerFactory.getLogger(UserRdbDao.class);

    public UserRdbDao(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public String getKeyName() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("user.id.name");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getSelectQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("user.select");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getInsertQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("user.insert");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUpdateQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("user.update");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getDeleteQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("user.delete");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DaoException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setIdentifier(rs.getLong("identifier"));
                user.setIin(rs.getLong("iin"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPatronymic(rs.getString("patronymic"));
                user.setRole(getDependence(Role.class, rs.getInt("role")));
                user.setBirthday(rs.getDate("birthday"));
                user.setActive(Boolean.valueOf(rs.getString("active")));

                result.add(user);
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setLong(1, object.getIdentifier());
            statement.setLong(2, object.getIin());
            if (object.getPassword() != null) {
                statement.setString(3, object.getPassword());
            } else {
                statement.setNull(3, Types.NULL);
            }
            statement.setString(4, object.getName());
            statement.setString(5, object.getSurname());
            statement.setString(6, object.getPatronymic());
            statement.setInt(7, object.getRole().getId());
            statement.setDate(8, object.getBirthday());
            statement.setString(9, String.valueOf(object.isActive()));
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setLong(1, object.getIdentifier());
            statement.setLong(2, object.getIin());
            statement.setString(3, object.getPassword());
            statement.setString(4, object.getName());
            statement.setString(5, object.getSurname());
            statement.setString(6, object.getPatronymic());
            statement.setInt(7, object.getRole().getId());
            statement.setDate(8, object.getBirthday());
            statement.setString(9, String.valueOf(object.isActive()));
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }
}
