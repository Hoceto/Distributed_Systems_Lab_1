package card_service;

import java.util.UUID;

public final class CreditCard {
    private String ccNum;
    private int expMonth;
    private int expYear;
    private int CVC;
    private int balance;
    private UUID cardId;
    private UUID ownerId;

    public CreditCard(int month, int year) {
        this.expMonth = month;
        this.expYear = year + 2;
        this.balance = 0;
        this.cardId = UUID.randomUUID();
    }

    public void setCcNum(String ccNum) {
        this.ccNum = this.ccNum;
    }

    public String getCcNum() {
        return this.ccNum;
    }

    public int getExpMonth() {
        return this.expMonth;
    }

    public void setExpMonth(int expMonth) {
        this.expMonth = expMonth;
    }

    public int getExpYear() {
        return this.expYear;
    }

    public void setExpYear(int expYear) {
        this.expYear = expYear;
    }

    public int getCVC() {
        return this.CVC;
    }

    void setCVC(int CVC) {
        this.CVC = CVC;
    }

    int getBalance() {
        return this.balance;
    }

    void setBalance(int balance) {
        this.balance = balance;
    }

    public UUID getCardId() {
        return this.cardId;
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }
}
