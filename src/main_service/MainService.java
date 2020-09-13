package main_service;

import card_service.CardManagement;
import card_service.CardService;
import card_service.CreditCard;
import deposit_service.DepositAccount;
import deposit_service.DepositManagement;
import deposit_service.DepositService;
import general_money_account.GeneralAccount;
import system_info.InfoService;
import user_service.User;
import user_service.UserConfig;
import user_service.UserManagement;
import worker_service.Worker;
import worker_service.WorkerConfig;
import worker_service.WorkerManagement;
import java.util.Date;
import java.util.UUID;

public final class MainService {
    private CardService cardService = new CardService(new CardManagement());
    private DepositService depositService = new DepositService(new DepositManagement());
    private UserConfig userConfig = new UserConfig(new UserManagement());
    private WorkerConfig workerConfig = new WorkerConfig(new WorkerManagement());
    private GeneralAccount generalAccount = new GeneralAccount(10000000);
    private InfoService infoService = new InfoService();
    private final int depositWage = 1000;
    private final int ccWage = 1000;

    public MainService() {
    }

    public UUID addUser(int userBirthDay, int userBirthMonth, int userBirthYear, String userName,
                        String UserSurname, String userPassId, String userPhoneNum,
                        String userEmail, String userTin, int userBalance) {
        User newUser = this.userConfig.registrateUser(userBirthDay, userBirthMonth, userBirthYear, userName, UserSurname, userPassId, userPhoneNum, userEmail, userTin, userBalance);
        System.out.println(this.infoService.userRegistrationInfo(newUser));
        return newUser.getUserId();
    }

    public void removeUser(UUID id) {
        this.userConfig.removeUser(id);
        System.out.println(this.infoService.userRemoveInfo(id));
    }

    public UUID addWorker(String workerName, String workerSurname, int workerPosition) {
        Worker newWorker = this.workerConfig.registrateWorker(workerName, workerSurname, workerPosition);
        System.out.println(this.infoService.workerRegistrationInfo(newWorker));
        return newWorker.getWorkerId();
    }

    public void removeWorker(UUID id) {
        this.workerConfig.removeWorker(id);
        System.out.println(this.infoService.workerRemoveInfo(id));
    }

    public UUID openCC(UUID workerId, UUID userId, String ccNum, int month, int year) {
        CreditCard card = this.cardService.openCC(userId, ccNum, month, year);
        this.workerConfig.addWage(workerId, 1000);
        UUID id = card.getCardId();
        this.userConfig.getUser(userId).addCreditCard(id);
        System.out.println(this.infoService.CCOpenInfo(card));
        return card.getCardId();
    }

    public void putMoneyCC(UUID workerId, UUID cardId, UUID userId, int money) {
        User user = this.userConfig.getUser(userId);
        if (money > user.getUserBalance()) {
            throw new IllegalArgumentException("Insufficient amount of money to put");
        } else {
            user.setUserBalance(user.getUserBalance() - money);
            int tax = (int)Math.ceil((double)money * 0.05D);
            this.generalAccount.setMoney(this.generalAccount.getMoney() + tax);
            this.cardService.putMoney(cardId, money - tax);
            this.workerConfig.addWage(workerId, ccWage);
            System.out.println(this.infoService.CCBalanceChange(cardId, money - tax, true, this.cardService.getCardBalance(cardId)));
        }
    }

    public void withdrawCC(UUID workerId, UUID cardId, UUID user_id, int money) {
        if (money > this.cardService.getCardBalance(cardId)) {
            throw new IllegalArgumentException("Insufficient amount of money to withdraw");
        } else {
            this.cardService.withdrawMoney(cardId, money);
            int tax = (int)Math.ceil((double)money * 0.05D);
            this.generalAccount.setMoney(this.generalAccount.getMoney() + tax);
            User user = this.userConfig.getUser(user_id);
            user.setUserBalance(user.getUserBalance() + money - tax);
            this.workerConfig.addWage(workerId, ccWage);
            System.out.println(this.infoService.CCBalanceChange(cardId, money, false, this.cardService.getCardBalance(cardId)));
        }
    }

    public void removeCC(UUID workerId, UUID userId, UUID cardId) {
        this.workerConfig.addWage(workerId, 1000);
        this.userConfig.getUser(userId).removeCreditCard(cardId);
        this.cardService.closeCC(cardId);
    }

    public void openDeposit(UUID workerId, UUID userId, Date openingDate, int depositMoney) {
        User user = this.userConfig.getUser(userId);
        if (depositMoney > user.getUserBalance()) {
            throw new IllegalArgumentException("Insufficient amount of money to put");
        } else {
            int operationTax = (int)Math.ceil((double)depositMoney * 0.05D);
            this.generalAccount.setMoney(this.generalAccount.getMoney() + operationTax);
            user.setUserBalance(user.getUserBalance() - depositMoney);
            DepositAccount deposit = this.depositService.openDeposit(userId, openingDate, depositMoney - operationTax);
            user.setUserDepositId(deposit.getDepositId());
            this.workerConfig.addWage(workerId, 1000);
            System.out.println(this.infoService.depositOpenInfo(user, deposit));
        }
    }

    public void withdrawDeposit(UUID workerId, UUID userId, Date withdrawDate) {
        User user = this.userConfig.getUser(userId);
        DepositAccount deposit = this.depositService.getDeposit(user.getUserDepositId());
        int return_money = this.depositService.withdrawDeposit(deposit.getDepositId(), withdrawDate);
        this.generalAccount.setMoney(this.generalAccount.getMoney() - return_money - deposit.getBalance());
        user.setUserBalance(user.getUserBalance() + return_money);
        System.out.println(this.infoService.depositWithdrawInfo(user, deposit, withdrawDate, return_money));
        this.depositService.closeDeposit(deposit.getDepositId());
        user.setUserDepositId(null);
        this.workerConfig.addWage(workerId, 1000);
    }

    public void printSummary() {
        System.out.println("-----------------------------НашБанк(тм)-----------------------------");
        System.out.println("Пользователи:");
        this.userConfig.printUsersInfo();
        System.out.println("-----\nРабочие:");
        this.workerConfig.printWorkersInfo();
        System.out.println("-----\nДепозиты:");
        this.depositService.printDepositsInfo();
        System.out.println("-----\nКредитные карты:");
        this.cardService.printCCInfo();
        System.out.println("-----\nБаланс общего банковского аккаунта:");
        System.out.println(generalAccount.getMoney());
    }
}