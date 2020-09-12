package Deposit_service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class Deposit_service {
    private Deposit_management manager;
    private static int deposit_count = 0;

    public Deposit_service(Deposit_management manager) {
        this.manager = manager;
    }

    public Deposit_account openDeposit(UUID user_id, Date opening, int money) {
        ++deposit_count;
        Deposit_account deposit = new Deposit_account(opening);
        deposit.setDeposit_id(String.valueOf(deposit_count));
        deposit.setOwner_id(user_id);
        deposit.setBalance(money);
        this.manager.addDeposit(deposit);
        return deposit;
    }

    public Deposit_account getDeposit(String depositID) {
        return this.manager.getDeposit(depositID);
    }

    public void closeDeposit(String depositID) {
        this.manager.removeDeposit(depositID);
    }

    public int withdrawDeposit(String deposit_id, Date withdraw_date) {
        Deposit_account deposit = this.manager.getDeposit(deposit_id);
        return deposit.withdrawBalance(withdraw_date);
    }

    public void printDepositsInfo() {
        Map<String, Deposit_account> deposit_storage = this.manager.getDeposit_storage();
        int iter = 1;

        for(Iterator var3 = deposit_storage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry<String, Deposit_account> entry = (Entry)var3.next();
            Deposit_account iter_deposit = (Deposit_account)entry.getValue();
            String opening_date = Deposit_account.getDate_format().format(iter_deposit.getOpening());
            System.out.println(String.format("\t%d, ID:%s, владелец: %s, дата открытия: %s, баланс: %s", iter, iter_deposit.getDeposit_id(), iter_deposit.getOwner_id(), opening_date, iter_deposit.getBalance()));
        }

        if (iter == 1) {
            System.out.println("Депозиты отсутствуют");
        }

    }
}