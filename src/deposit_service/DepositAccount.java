package deposit_service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public final class DepositAccount {
    private int balance = 0;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date openingDate;
    private double percent = 0.095D;
    private UUID depositId;
    private UUID ownerId;

    DepositAccount(Date openingDate) {
        this.openingDate = openingDate;
    }

    void setBalance(int money) {
        this.balance = money;
    }

    public int getBalance() {
        return this.balance;
    }

    public Date getOpeningDate() {
        return this.openingDate;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    int withdrawBalance(Date withdraw_date) {
        return (int)(this.balance * this.calculateMultiplier(withdraw_date));
    }

    public UUID getDepositId() {
        return this.depositId;
    }

    void setDepositId() {
        this.depositId = UUID.randomUUID();
    }

    UUID getOwnerId() {
        return this.ownerId;
    }

    void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    double calculateMultiplier(Date withdraw_date) {
        Calendar cal = Calendar.getInstance();
        if (!this.openingDate.before(withdraw_date)) {
            throw new IllegalArgumentException("Wrong withdraw data (must always be after opening");
        } else {
            cal.setTime(this.openingDate);

            int c;
            for(c = 0; cal.getTime().before(withdraw_date); ++c) {
                cal.add(2, 1);
            }

            return Math.pow(1.0D + this.percent, (double)(c - 1));
        }
    }
}