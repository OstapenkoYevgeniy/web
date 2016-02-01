package com.john.dao.rdb;

import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.entity.TypePaymentCard;
import com.john.property.SqlQueryPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TypePaymentCardRdbDao extends AbstractRdbDao<TypePaymentCard, Integer> {
    Logger log = org.slf4j.LoggerFactory.getLogger(TypePaymentCardRdbDao.class);

    public TypePaymentCardRdbDao(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public String getKeyName() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("type.payment.card.id.name");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getSelectQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("type.payment.card.select");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getInsertQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("type.payment.card.insert");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUpdateQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("type.payment.card.update");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getDeleteQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("type.payment.card.delete");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected List<TypePaymentCard> parseResultSet(ResultSet rs) throws DaoException {
        List<TypePaymentCard> result = new ArrayList<>();
        try {
            while (rs.next()) {
                TypePaymentCard typePaymentCard = new TypePaymentCard();
                typePaymentCard.setId(rs.getInt("id"));
                typePaymentCard.setName(rs.getString("name"));
                result.add(typePaymentCard);
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TypePaymentCard object) throws DaoException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TypePaymentCard object) throws DaoException {
        try {
            statement.setString(1, object.getName());
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }
}
