package card_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class CardManagement {
    private Map<UUID, CreditCard> cardStorage;

    public CardManagement() {
        cardStorage = new HashMap<>();
    }

    void addCard(CreditCard card) {
        UUID cardId = card.getCardId();
        this.cardStorage.put(cardId, card);
    }

    void removeCard(UUID cardId) {
        this.cardStorage.remove(cardId);
    }

    CreditCard getCard(UUID cardId) {
        return this.cardStorage.get(cardId);
    }

    Map<UUID, CreditCard> getCardStorage() {
        return this.cardStorage;
    }
}