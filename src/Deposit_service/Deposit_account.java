package Deposit_service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Deposit_account {
    private int balance = 0;
    private static final DateFormat date_format = new SimpleDateFormat("dd/MM/yyyy");
    private Date opening;
    private double percent = 0.095D;
    private String deposit_id;
    private UUID owner_id;

    public Deposit_account(Date opening) {
        this.opening = opening;
    }

    public void setBalance(int money) {
        this.balance = money;
    }

    public int getBalance() {
        return this.balance;
    }

    public Date getOpening() {
        return this.opening;
    }

    public static DateFormat getDate_format() {
        return date_format;
    }

    public int withdrawBalance(Date withdraw_date) {
        return (int)((double)this.balance * this.calculateMultiplier(withdraw_date));
    }

    public String getDeposit_id() {
        return this.deposit_id;
    }

    public void setDeposit_id(String deposit_id) {
        this.deposit_id = deposit_id;
    }

    public UUID getOwner_id() {
        return this.owner_id;
    }

    public void setOwner_id(UUID owner_id) {
        this.owner_id = owner_id;
    }

    double calculateMultiplier(Date withdraw_date) {
        Calendar cal = Calendar.getInstance();
        if (!this.opening.before(withdraw_date)) {
            throw new IllegalArgumentException("Wrong withdraw data (must always be after opening");
        } else {
            cal.setTime(this.opening);

            int c;
            for(c = 0; cal.getTime().before(withdraw_date); ++c) {
                cal.add(2, 1);
            }

            return Math.pow(1.0D + this.percent, (double)(c - 1));
        }
    }
}