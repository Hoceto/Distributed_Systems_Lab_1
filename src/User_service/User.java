package User_service;

import Date_src.User_date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class User {
    private UUID user_id = UUID.randomUUID();
    private int balance;
    private String name;
    private String surname;
    private String phone_number;
    private User_date birthday_date;
    private String passid;
    private String TIN;
    private String email;
    private ArrayList<String> card_list = new ArrayList();
    private String deposit_id = null;

    public User() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUser_id() {
        return this.user_id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setPassid(String passid) {
        this.passid = passid;
    }

    public String getPassid() {
        return this.passid;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setBirthday_date(User_date birthday_date) {
        this.birthday_date = birthday_date;
    }

    public User_date getBirthday_date() {
        return this.birthday_date;
    }

    public void setTIN(String TIN) {
        this.TIN = TIN;
    }

    public String getTIN() {
        return this.TIN;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setDeposit_id(String deposit_id) {
        this.deposit_id = deposit_id;
    }

    public String getDeposit_id() {
        return this.deposit_id;
    }

    public void addCreditCard(String card_id) {
        this.card_list.add(card_id);
    }

    public void removeCreditCard(String card_id) {
        this.card_list.remove(card_id);
    }

    public ArrayList<String> getCCList() {
        return this.card_list;
    }

    public String toString() {
        return String.format("Имя: %s %s, UUID: %s баланс: %d, депозит: %s, кредитных карт: %d", this.name, this.surname, this.user_id, this.balance, Objects.isNull(this.deposit_id) ? "есть" : "отсутствует", this.card_list.size());
    }
}