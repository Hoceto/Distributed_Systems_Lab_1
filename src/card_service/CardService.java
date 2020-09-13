package card_service;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public final class CardService {
    private CardManagement manager;

    public CardService(CardManagement manager) {
        this.manager = manager;
    }

    public CreditCard openCC(UUID ccOpener, String ccNum, int month, int year) {
        CreditCard newCard = new CreditCard(month, year);
        newCard.setCcNum(ccNum);
        newCard.setCVC(ThreadLocalRandom.current().nextInt(100, 1000));
        newCard.setOwnerId(ccOpener);
        this.manager.addCard(newCard);
        return newCard;
    }

    public void closeCC(UUID cardId) {
        this.manager.removeCard(cardId);
    }

    public void putMoney(UUID cardId, int money) {
        CreditCard card = this.manager.getCard(cardId);
        card.setBalance(card.getBalance() + money);
    }

    public void withdrawMoney(UUID cardId, int withdrawMoney) {
        CreditCard card = this.manager.getCard(cardId);
        card.setBalance(card.getBalance() - withdrawMoney);
    }

    public int getCardBalance(UUID cardId) {
        return this.manager.getCard(cardId).getBalance();
    }

    public void printCCInfo() {
        Map<UUID, CreditCard> cardStorage = this.manager.getCardStorage();
        int iter = 1;

        for(Iterator var3 = cardStorage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry entry = (Entry)var3.next();
            CreditCard iterCard = (CreditCard)entry.getValue();
            System.out.println(String.format("\t%d, ID:%s, владелец: %s, баланс: %s", iter, iterCard.getCardId(), iterCard.getOwnerId(), iterCard.getBalance()));
        }

        if (iter == 1) {
            System.out.println("Кредитные карты отсутствуют");
        }

    }
}