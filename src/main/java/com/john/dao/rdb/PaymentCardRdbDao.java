package com.john.dao.rdb;

import com.john.dao.DaoException;
import com.john.dao.DaoFactory;
import com.john.entity.PaymentCard;
import com.john.entity.TypePaymentCard;
import com.john.entity.User;
import com.john.property.SqlQueryPropertyManager;
import org.slf4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentCardRdbDao extends AbstractRdbDao<PaymentCard, Long> {
    Logger log = org.slf4j.LoggerFactory.getLogger(PaymentCardRdbDao.class);

    public PaymentCardRdbDao(Connection connection, DaoFactory daoFactory) {
        super(connection, daoFactory);
    }

    @Override
    public String getKeyName() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("payment.card.id.name");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getSelectQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("payment.card.select");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getInsertQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("payment.card.insert");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getUpdateQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("payment.card.update");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    public String getDeleteQuery() throws DaoException {
        try {
            return SqlQueryPropertyManager.getInstance().getProperty("payment.card.delete");
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected List<PaymentCard> parseResultSet(ResultSet rs) throws DaoException {
        List<PaymentCard> result = new ArrayList<>();
        try {
            while (rs.next()) {
                PaymentCard paymentCard = new PaymentCard();
                paymentCard.setId(rs.getLong("id"));
                paymentCard.setNumber(rs.getLong("number"));
                paymentCard.setUser(getDependence(User.class, rs.getLong("user")));
                paymentCard.setTypePaymentCard(getDependence(TypePaymentCard.class, rs.getInt("type_payment_card")));
                paymentCard.setCodeword(rs.getString("codeword"));
                paymentCard.setCardExpiryDate(rs.getDate("card_expiry_date"));
                paymentCard.setSecurityCode(rs.getInt("security_code"));
                paymentCard.setBalanceKzt(rs.getDouble("balance_kzt"));
                paymentCard.setBalanceRub(rs.getDouble("balance_rub"));
                paymentCard.setBalanceUsd(rs.getDouble("balance_usd"));
                paymentCard.setBlocked(Boolean.valueOf(rs.getString("blocked")));

                result.add(paymentCard);
            }
            return result;
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, PaymentCard object) throws DaoException {
        try {
            statement.setLong(1, object.getNumber());
            statement.setLong(2, object.getUser().getId());
            statement.setInt(3, object.getTypePaymentCard().getId());
            statement.setString(4, object.getCodeword());
            statement.setDate(5, object.getCardExpiryDate());
            statement.setInt(6, object.getSecurityCode());
            statement.setDouble(7, object.getBalanceKzt());
            statement.setDouble(8, object.getBalanceRub());
            statement.setDouble(9, object.getBalanceUsd());
            statement.setString(10, String.valueOf(object.isBlocked()));
        } catch (Exception e) {
            log.error("", e);
            throw new DaoException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, PaymentCard object) throws DaoException {
        prepareStatementForInsert(statement, object);
    }
}
