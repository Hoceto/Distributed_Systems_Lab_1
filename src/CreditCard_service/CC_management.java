package CreditCard_service;

import java.util.HashMap;
import java.util.Map;

public class CC_management {
    private Map<String, Credit_card> CC_storage = new HashMap();

    public CC_management() {
    }

    public void addCard(Credit_card card) {
        this.CC_storage.put(card.getCard_id(), card);
    }

    public void removeCard(String id) {
        this.CC_storage.remove(id);
    }

    public Credit_card getCard(String id) {
        return (Credit_card)this.CC_storage.get(id);
    }

    public Map<String, Credit_card> getCC_storage() {
        return this.CC_storage;
    }
}