package deposit_service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public final class DepositService {
    private DepositManagement manager;

    public DepositService(DepositManagement manager) {
        this.manager = manager;
    }

    public DepositAccount openDeposit(UUID userId, Date openingDate, int depositMoney) {
        DepositAccount deposit = new DepositAccount(openingDate);
        deposit.setDepositId();
        deposit.setOwnerId(userId);
        deposit.setBalance(depositMoney);
        this.manager.addDeposit(deposit);
        return deposit;
    }

    public DepositAccount getDeposit(UUID depositID) {
        return this.manager.getDeposit(depositID);
    }

    public void closeDeposit(UUID depositID) {
        this.manager.removeDeposit(depositID);
    }

    public int withdrawDeposit(UUID depositId, Date withdrawDate) {
        DepositAccount deposit = this.manager.getDeposit(depositId);
        return deposit.withdrawBalance(withdrawDate);
    }

    public void printDepositsInfo() {
        Map<UUID, DepositAccount> deposit_storage = this.manager.getDepositStorage();
        int iter = 1;

        for(Iterator var3 = deposit_storage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry entry = (Entry)var3.next();
            DepositAccount iter_deposit = (DepositAccount)entry.getValue();
            String opening_date = iter_deposit.getDateFormat().format(iter_deposit.getOpeningDate());
            System.out.println(String.format("\t%d, ID:%s, владелец: %s, дата открытия: %s, баланс: %s", iter, iter_deposit.getDepositId(), iter_deposit.getOwnerId(), opening_date, iter_deposit.getBalance()));
        }

        if (iter == 1) {
            System.out.println("Депозиты отсутствуют");
        }

    }
}