package Deposit_service;

import java.util.HashMap;
import java.util.Map;

public class Deposit_management {
    private Map<String, Deposit_account> Deposit_storage = new HashMap();

    public Deposit_management() {
    }

    public void addDeposit(Deposit_account deposit) {
        this.Deposit_storage.put(deposit.getDeposit_id(), deposit);
    }

    public void removeDeposit(String id) {
        this.Deposit_storage.remove(id);
    }

    public Deposit_account getDeposit(String id) {
        return (Deposit_account)this.Deposit_storage.get(id);
    }

    Map<String, Deposit_account> getDeposit_storage() {
        return this.Deposit_storage;
    }
}