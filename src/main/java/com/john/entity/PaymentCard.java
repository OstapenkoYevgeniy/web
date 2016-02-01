package com.john.entity;

import com.john.dao.DaoException;
import com.john.dao.Identified;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

@BaseEntityDao
public class PaymentCard implements Identified<Long> {
    private Long id;
    private long number;
    private User user;
    private TypePaymentCard typePaymentCard;
    private String codeword;
    private Date cardExpiryDate;
    private int securityCode;
    private double balanceKzt;
    private double balanceRub;
    private double balanceUsd;
    private boolean blocked;

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

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TypePaymentCard getTypePaymentCard() {
        return typePaymentCard;
    }

    public void setTypePaymentCard(TypePaymentCard typePaymentCard) {
        this.typePaymentCard = typePaymentCard;
    }

    public String getCodeword() {
        return codeword;
    }

    public void setCodeword(String codeword) {
        this.codeword = codeword;
    }

    public Date getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(Date cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public double getBalanceKzt() {
        return balanceKzt;
    }

    public void setBalanceKzt(double balanceKzt) {
        this.balanceKzt = balanceKzt;
    }

    public double getBalanceRub() {
        return balanceRub;
    }

    public void setBalanceRub(double balanceRub) {
        this.balanceRub = balanceRub;
    }

    public double getBalanceUsd() {
        return balanceUsd;
    }

    public void setBalanceUsd(double balanceUsd) {
        this.balanceUsd = balanceUsd;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean refillBalance(String currency, double amountTransfer) {
        if (blocked) {
            return false;
        } else {
            switch (currency) {
                case "kzt":
                    balanceKzt += amountTransfer;
                    return true;
                case "usd":
                    balanceUsd += amountTransfer;
                    return true;
                case "rub":
                    balanceRub += amountTransfer;
                    return true;
                default:
                    return false;
            }
        }
    }

    public boolean removeBalance(String currency, double amountTransfer) {
        if (blocked) {
            return false;
        } else {
            switch (currency) {
                case "kzt":
                    if (balanceKzt >= amountTransfer) {
                        balanceKzt -= amountTransfer;
                        return true;
                    } else {
                        return false;
                    }
                case "usd":
                    if (balanceUsd >= amountTransfer) {
                        balanceUsd -= amountTransfer;
                        return true;
                    } else {
                        return false;
                    }
                case "rub":
                    if (balanceRub >= amountTransfer) {
                        balanceRub -= amountTransfer;
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        }
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "id=" + id +
                ", user=" + user +
                ", typePaymentCard=" + typePaymentCard +
                ", number=" + number +
                ", codeword='" + codeword + '\'' +
                ", cardExpiryDate=" + cardExpiryDate +
                ", securityCode=" + securityCode +
                ", balanceKzt=" + balanceKzt +
                ", blocked=" + blocked +
                '}';
    }

    /**
     * Converts a line of the "MMYY" format in object of java.sql.Date
     *
     * @param date Card expiry date in a format "MMYY"
     * @return java.sql.Date
     */
    public static Date cardExpiryDateValueOf(String date) {
        if (date.length() != 4) {
            throw new IllegalArgumentException();
        }
        String month = date.substring(0, 2);
        String year = "20" + date.substring(2, 4);
        return Date.valueOf(year + "-" + month + "-" + "01");
    }

}
