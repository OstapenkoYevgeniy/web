package com.john.dao.rdb;

import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.entity.Role;
import com.john.property.SqlQueryPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRdbDao extends AbstractRdbDao<Role, Integer> {
    Logger log = org.slf4j.LoggerFactory.getLogger(RoleRdbDao.class);

    public RoleRdbDao(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public String getKeyName() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("role.id.name");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getSelectQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("role.select");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getInsertQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("role.insert");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUpdateQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("role.update");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getDeleteQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("role.delete");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws DaoException {
        List<Role> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setSessionLifeTime(rs.getInt("session_life_time"));
                result.add(role);
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws DaoException {
        try {
            statement.setString(1, object.getName());
            statement.setInt(2, object.getSessionLifeTime());
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws DaoException {
        prepareStatementForInsert(statement, object);
    }
}
