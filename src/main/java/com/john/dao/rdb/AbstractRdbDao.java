package com.john.dao.rdb;

import com.john.dao.Dao;
import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.dao.Identified;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractRdbDao<T extends Identified, K> implements Dao<T, K> {
    Logger log = org.slf4j.LoggerFactory.getLogger(AbstractRdbDao.class);
    private DaoFactory daoFactory;
    protected final Connection connection;

    public AbstractRdbDao(Connection connection, DaoFactory daoFactory) {
        this.connection = connection;
        this.daoFactory = daoFactory;
    }

    protected <T> T getDependence(Class entity, Object key) throws DaoException {
        return (T) daoFactory.getDao(entity).getByPK(key);
    }

    public abstract String getKeyName() throws DaoException;

    public abstract String getSelectQuery() throws DaoException;

    public abstract String getInsertQuery() throws DaoException;

    public abstract String getUpdateQuery() throws DaoException;

    public abstract String getDeleteQuery() throws DaoException;

    protected abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;

    @Override
    public T insert(T object) throws DaoException {
        if (object.getId() != null) {
            log.error("There is no object in storage of data!");
            throw new DaoException("There is no object in storage of data!");
        }
        String sql = getInsertQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            if (statement.executeUpdate() != 1) {
                log.error("Error of preservation of object!");
                throw new DaoException("Error of preservation of object!");
            }
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            object.setId(rs);
            return object;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int update(T object) throws DaoException {
        String sql = getUpdateQuery();
        if (object.getId() == null) {
            log.error("There is no object in storage of data!");
            throw new DaoException("There is no object in storage of data!");
        }
        sql += "WHERE id = " + object.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForUpdate(statement, object);
            return statement.executeUpdate();
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public int delete(T object) throws DaoException {
        String sql = getDeleteQuery();
        if (object.getId() == null) {
            log.error("There is no object in storage of data!");
            throw new DaoException("There is no object in storage of data!");
        }
        sql += " WHERE " + getKeyName() + " = " + object.getId();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            return statement.executeUpdate();
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public T getByPK(K key) throws DaoException {
        return getByColumn(getKeyName(), String.valueOf(key));
    }

    @Override
    public T getByColumn(String column, String value) throws DaoException {
        List<T> result = getAllByColumn(column, value);
        if (result.size() == 0) {
            return null;
        }
        return result.iterator().next();
    }

    public List<T> getAllByColumn(String column, String value) throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE " + column + " = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, value);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
        return list;
    }

    @Override
    public List<T> getAll() throws DaoException {
        String sql = getSelectQuery();
        return getAll(sql);
    }

    @Override
    public List<T> getAll(int limit, int offset) throws DaoException {
        String sql = getSelectQuery();
        sql += " LIMIT " + limit + " OFFSET " + offset;
        return getAll(sql);
    }

    @Override
    public long getCount() throws DaoException {
        String sql = getSelectQuery();
        sql = sql.replace("*", "COUNT(*)");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong(1);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    private List<T> getAll(String sql) throws DaoException {
        List<T> list;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
        return list;
    }
}