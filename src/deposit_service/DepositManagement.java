package deposit_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class DepositManagement {
    private Map<UUID, DepositAccount> depositStorage;

    public DepositManagement() {
        depositStorage = new HashMap<>();
    }

    void addDeposit(DepositAccount deposit) {
        this.depositStorage.put(deposit.getDepositId(), deposit);
    }

    void removeDeposit(UUID depositId) {
        this.depositStorage.remove(depositId);
    }

    DepositAccount getDeposit(UUID depositId) {
        return this.depositStorage.get(depositId);
    }

    Map<UUID, DepositAccount> getDepositStorage() {
        return this.depositStorage;
    }
}