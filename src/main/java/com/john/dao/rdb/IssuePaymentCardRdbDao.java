package com.john.dao.rdb;

import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.entity.IssuePaymentCard;
import com.john.entity.TypePaymentCard;
import com.john.property.SqlQueryPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IssuePaymentCardRdbDao extends AbstractRdbDao<IssuePaymentCard, Long> {
    Logger log = org.slf4j.LoggerFactory.getLogger(IssuePaymentCardRdbDao.class);

    public IssuePaymentCardRdbDao(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public String getKeyName() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("issue.payment.card.id.name");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getSelectQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("issue.payment.card.select");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getInsertQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("issue.payment.card.insert");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUpdateQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("issue.payment.card.update");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getDeleteQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("issue.payment.card.delete");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected List<IssuePaymentCard> parseResultSet(ResultSet rs) throws DaoException {
        List<IssuePaymentCard> result = new ArrayList<>();
        try {
            while (rs.next()) {
                IssuePaymentCard issuePaymentCard = new IssuePaymentCard();
                issuePaymentCard.setId(rs.getLong("id"));
                issuePaymentCard.setIin(rs.getLong("iin"));
                issuePaymentCard.setPhone(rs.getLong("phone"));
                issuePaymentCard.setClientName(rs.getString("client_name"));
                issuePaymentCard.setTypePaymentCard(getDependence(TypePaymentCard.class, rs.getLong("type_payment_card")));
                result.add(issuePaymentCard);
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, IssuePaymentCard object) throws DaoException {
        try {
            statement.setLong(1, object.getIin());
            statement.setLong(2, object.getPhone());
            statement.setString(3, object.getClientName());
            statement.setInt(4, object.getTypePaymentCard().getId());
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, IssuePaymentCard object) throws DaoException {
        prepareStatementForInsert(statement, object);
    }
}
