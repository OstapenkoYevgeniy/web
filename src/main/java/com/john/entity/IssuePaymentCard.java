package com.john.entity;

import com.john.dao.DaoException;
import com.john.dao.Identified;

import java.sql.ResultSet;
import java.sql.SQLException;

@BaseEntityDao
public class IssuePaymentCard implements Identified<Long> {
    private Long id;
    private long iin;
    private long phone;
    private String clientName;
    private TypePaymentCard typePaymentCard;

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

    public long getIin() {
        return iin;
    }

    public void setIin(long iin) {
        this.iin = iin;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public TypePaymentCard getTypePaymentCard() {
        return typePaymentCard;
    }

    public void setTypePaymentCard(TypePaymentCard typePaymentCard) {
        this.typePaymentCard = typePaymentCard;
    }

    @Override
    public String toString() {
        return "IssuePaymentCard{" +
                "id=" + id +
                ", typePaymentCard=" + typePaymentCard +
                ", phone=" + phone +
                ", iin=" + iin +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
