package CreditCard_service;

import java.util.UUID;

public class Credit_card {
    private String cc_num;
    private int exp_month;
    private int exp_year;
    private int CVC;
    private int balance;
    private String card_id;
    private UUID owner_id;

    public Credit_card(int month, int year) {
        this.exp_month = month;
        this.exp_year = year + 2;
        this.balance = 0;
    }

    public void setCc_num(String cc_num) {
        this.cc_num = cc_num;
    }

    public String getCc_num() {
        return this.cc_num;
    }

    public int getExp_month() {
        return this.exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }

    public int getExp_year() {
        return this.exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public int getCVC() {
        return this.CVC;
    }

    public void setCVC(int CVC) {
        this.CVC = CVC;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getCard_id() {
        return this.card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public UUID getOwner_id() {
        return this.owner_id;
    }

    public void setOwner_id(UUID owner_id) {
        this.owner_id = owner_id;
    }
}
