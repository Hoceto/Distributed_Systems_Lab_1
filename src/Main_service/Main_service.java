package Main_service;

import CreditCard_service.CC_management;
import CreditCard_service.CC_service;
import CreditCard_service.Credit_card;
import Deposit_service.Deposit_account;
import Deposit_service.Deposit_management;
import Deposit_service.Deposit_service;
import General_money_account.General_account;
import System_info.Info_service;
import User_service.User;
import User_service.User_config;
import User_service.User_management;
import Worker_service.Worker;
import Worker_service.Worker_config;
import Worker_service.Worker_management;
import java.util.Date;
import java.util.UUID;

public class Main_service {
    private CC_service cc_service = new CC_service(new CC_management());
    private Deposit_service deposit_service = new Deposit_service(new Deposit_management());
    private User_config user_config = new User_config(new User_management());
    private Worker_config worker_config = new Worker_config(new Worker_management());
    private General_account general_account = new General_account(10000000);
    private Info_service info_service = new Info_service();
    private final int deposit_wage = 1000;
    private final int CC_wage = 1000;

    public Main_service() {
    }

    public UUID addUser(int day, int month, int year, String name, String surname, String passid, String phone_num, String email, String TIN, int balance) {
        User new_user = this.user_config.registrateUser(day, month, year, name, surname, passid, phone_num, email, TIN, balance);
        System.out.println(this.info_service.userRegistrationInfo(new_user));
        return new_user.getUser_id();
    }

    public void removeUser(UUID id) {
        this.user_config.removeUser(id);
        System.out.println(this.info_service.userRemoveInfo(id));
    }

    public String addWorker(String name, String surname, int position) {
        Worker new_worker = this.worker_config.registrateWorker(name, surname, position);
        System.out.println(this.info_service.workerRegistrationInfo(new_worker));
        return new_worker.getPid();
    }

    public void removeWorker(String id) {
        this.worker_config.removeWorker(id);
        System.out.println(this.info_service.workerRemoveInfo(id));
    }

    public String openCC(String worker_id, UUID user_id, String cc_num, int month, int year) {
        Credit_card card = this.cc_service.openCC(user_id, cc_num, month, year);
        this.worker_config.addWage(worker_id, 1000);
        String id = card.getCard_id();
        this.user_config.getUser(user_id).addCreditCard(id);
        System.out.println(this.info_service.CCOpenInfo(card));
        return card.getCard_id();
    }

    public void putMoneyCC(String worker_id, String card_id, UUID user_id, int money) {
        User user = this.user_config.getUser(user_id);
        if (money > user.getBalance()) {
            throw new IllegalArgumentException("Insufficient amount of money to put");
        } else {
            user.setBalance(user.getBalance() - money);
            int tax = (int)Math.ceil((double)money * 0.05D);
            this.general_account.setMoney(this.general_account.getMoney() + tax);
            this.cc_service.putMoney(card_id, money - tax);
            this.worker_config.addWage(worker_id, 1000);
            System.out.println(this.info_service.CCBalanceChange(card_id, money - tax, true, this.cc_service.getCardBalance(card_id)));
        }
    }

    public void withdrawCC(String worker_id, String card_id, UUID user_id, int money) {
        if (money > this.cc_service.getCardBalance(card_id)) {
            throw new IllegalArgumentException("Insufficient amount of money to withdraw");
        } else {
            this.cc_service.withdrawMoney(card_id, money);
            int tax = (int)Math.ceil((double)money * 0.05D);
            this.general_account.setMoney(this.general_account.getMoney() + tax);
            User user = this.user_config.getUser(user_id);
            user.setBalance(user.getBalance() + money - tax);
            this.worker_config.addWage(worker_id, 1000);
            System.out.println(this.info_service.CCBalanceChange(card_id, money, false, this.cc_service.getCardBalance(card_id)));
        }
    }

    public void removeCC(String worker_id, UUID user_id, String card_id) {
        this.worker_config.addWage(worker_id, 1000);
        this.user_config.getUser(user_id).removeCreditCard(card_id);
        this.cc_service.closeCC(card_id);
    }

    public void openDeposit(String worker_id, UUID user_id, Date opening, int money) {
        User user = this.user_config.getUser(user_id);
        if (money > user.getBalance()) {
            throw new IllegalArgumentException("Insufficient amount of money to put");
        } else {
            int tax = (int)Math.ceil((double)money * 0.05D);
            this.general_account.setMoney(this.general_account.getMoney() + tax);
            user.setBalance(user.getBalance() - money);
            Deposit_account deposit = this.deposit_service.openDeposit(user_id, opening, money - tax);
            user.setDeposit_id(deposit.getDeposit_id());
            this.worker_config.addWage(worker_id, 1000);
            System.out.println(this.info_service.depositOpenInfo(user, deposit));
        }
    }

    public void withdrawDeposit(String worker_id, UUID user_id, Date withdraw) {
        User user = this.user_config.getUser(user_id);
        Deposit_account deposit = this.deposit_service.getDeposit(user.getDeposit_id());
        int return_money = this.deposit_service.withdrawDeposit(deposit.getDeposit_id(), withdraw);
        this.general_account.setMoney(this.general_account.getMoney() - return_money - deposit.getBalance());
        user.setBalance(user.getBalance() + return_money);
        System.out.println(this.info_service.depositWithdrawInfo(user, deposit, withdraw, return_money));
        this.deposit_service.closeDeposit(deposit.getDeposit_id());
        user.setDeposit_id((String)null);
        this.worker_config.addWage(worker_id, 1000);
    }

    public void printSummary() {
        System.out.println("-----------------------------НашБанк(тм)-----------------------------");
        System.out.println("Пользователи:");
        this.user_config.printUsersInfo();
        System.out.println("-----\nРабочие:");
        this.worker_config.printWorkersInfo();
        System.out.println("-----\nДепозиты:");
        this.deposit_service.printDepositsInfo();
        System.out.println("-----\nКредитные карты:");
        this.cc_service.printCCInfo();
        System.out.println("-----\nБаланс общего банковского аккаунта:");
        System.out.println(general_account.getMoney());
    }
}