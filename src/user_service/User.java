package user_service;

import date_src.UserDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public final class User {
    private UUID userId;
    private int userBalance;
    private String userName;
    private String userSurname;
    private String userPhoneNumber;
    private UserDate birthday_date;
    private String userPassportId;
    private String userTin;
    private String userEmail;
    private ArrayList<UUID> userCardList;
    private UUID userDepositId = null;

    User() {
        userId = UUID.randomUUID();
        userCardList = new ArrayList<>();
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return this.userName;
    }

    public UUID getUserId() {
        return this.userId;
    }

    void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserSurname() {
        return this.userSurname;
    }

    void setUserPassportId(String userPassportId) {
        this.userPassportId = userPassportId;
    }

    public String getUserPassportId() {
        return this.userPassportId;
    }

    void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserPhoneNumber() {
        return this.userPhoneNumber;
    }

    void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    void setBirthday_date(UserDate birthday_date) {
        this.birthday_date = birthday_date;
    }

    public UserDate getBirthday_date() {
        return this.birthday_date;
    }

    void setUserTin(String userTin) {
        this.userTin = userTin;
    }

    public String getUserTin() {
        return this.userTin;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public int getUserBalance() {
        return this.userBalance;
    }

    public void setUserDepositId(UUID userDepositId) {
        this.userDepositId = userDepositId;
    }

    public UUID getUserDepositId() {
        return this.userDepositId;
    }

    public void addCreditCard(UUID cardId) {
        this.userCardList.add(cardId);
    }

    public void removeCreditCard(UUID cardId) {
        this.userCardList.remove(cardId);
    }

    ArrayList<UUID> getCCList() {
        return this.userCardList;
    }

    public String toString() {
        return String.format("Имя: %s %s, UUID: %s баланс: %d, депозит: %s, кредитных карт: %d", this.userName, this.userSurname, this.userId, this.userBalance, Objects.isNull(this.userDepositId) ? "есть" : "отсутствует", this.userCardList.size());
    }
}