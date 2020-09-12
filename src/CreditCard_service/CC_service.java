package CreditCard_service;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;

public class CC_service {
    private CC_management manager;
    private static int CC_count = 0;

    public CC_service(CC_management manager) {
        this.manager = manager;
    }

    public Credit_card openCC(UUID opener, String cc_num, int month, int year) {
        Credit_card card = new Credit_card(month, year);
        ++CC_count;
        card.setCard_id(String.valueOf(CC_count));
        card.setCc_num(cc_num);
        card.setCVC(ThreadLocalRandom.current().nextInt(100, 1000));
        card.setCard_id(String.valueOf(CC_count));
        card.setOwner_id(opener);
        this.manager.addCard(card);
        return card;
    }

    public void closeCC(String card_id) {
        this.manager.removeCard(card_id);
    }

    public void putMoney(String card_id, int money) {
        Credit_card card = this.manager.getCard(card_id);
        card.setBalance(card.getBalance() + money);
    }

    public void withdrawMoney(String card_id, int money) {
        Credit_card card = this.manager.getCard(card_id);
        card.setBalance(card.getBalance() - money);
    }

    public int getCardBalance(String card_id) {
        return this.manager.getCard(card_id).getBalance();
    }

    public void printCCInfo() {
        Map<String, Credit_card> CC_storage = this.manager.getCC_storage();
        int iter = 1;

        for(Iterator var3 = CC_storage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry<String, Credit_card> entry = (Entry)var3.next();
            Credit_card iter_CC = (Credit_card)entry.getValue();
            System.out.println(String.format("\t%d, ID:%s, владелец: %s, баланс: %s", iter, iter_CC.getCard_id(), iter_CC.getOwner_id(), iter_CC.getBalance()));
        }

        if (iter == 1) {
            System.out.println("Кредитные карты отсутствуют");
        }

    }
}