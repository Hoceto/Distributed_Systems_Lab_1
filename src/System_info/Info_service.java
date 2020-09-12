package System_info;

import CreditCard_service.Credit_card;
import Deposit_service.Deposit_account;
import User_service.User;
import Worker_service.Worker;
import java.util.Date;
import java.util.UUID;

public class Info_service {
    public Info_service() {
    }

    public String depositOpenInfo(User user, Deposit_account deposit) {
        Object[] var10001 = new Object[]{deposit.getDeposit_id(), user.getName(), user.getSurname(), Deposit_account.getDate_format().format(deposit.getOpening()), deposit.getBalance()};
        return "--------------------------Открытие депозита--------------------------------\n" + String.format("Новый депозит открыт. ID: %s. Владелец: %s %s, дата открытия: %s, баланс:%d\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String depositWithdrawInfo(User user, Deposit_account deposit, Date withdraw_date, int withdraw_money) {
        Object[] var10001 = new Object[]{deposit.getDeposit_id(), user.getName(), user.getSurname(), Deposit_account.getDate_format().format(withdraw_date), withdraw_money};
        return "\n----------------------------Закрытие депозита----------------------------\n" + String.format("Депозит %s закрыт. Владелец %s %s, дата закрытия: %s, выведено со счета: %d\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String userRegistrationInfo(User user) {
        Object[] var10001 = new Object[]{user.getUser_id(), user.getName(), user.getSurname(), user.getEmail(), user.getPhone_number()};
        return "\n----------------------------Регистрация пользователя---------------------\n" + String.format("Зарегистрирован новый пользователь. UUID: %s\n  -Имя: %s %s\n  -Почта: %s,\n  -Тел. номер: %s\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String userRemoveInfo(UUID id) {
        Object[] var10001 = new Object[]{id};
        return "----------------------------Удаление пользователя--------------------------\n" + String.format("Пользователь %s был удален\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String workerRegistrationInfo(Worker worker) {
        Object[] var10001 = new Object[]{worker.getPid(), worker.getName(), worker.getSurname(), worker.getPosition_name()};
        return "---------------------------Регистрация работника---------------------------\n" + String.format("Зарегистрирован новый работник. ID: %s\n  -Имя: %s %s\n  -Должность: %s\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String workerRemoveInfo(String id) {
        Object[] var10001 = new Object[]{id};
        return "---------------------------Удаление работника------------------------------\n" + String.format("Работник %s был удален\n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String CCOpenInfo(Credit_card card) {
        Object[] var10001 = new Object[]{card.getCard_id(), card.getOwner_id(), card.getCc_num(), card.getExp_month(), card.getExp_year(), card.getCVC()};
        return "---------------------------Открытие кред. карты----------------------------\n" + String.format("Открыта новая кредитная карта. ID: %s\n -Владелец: %s\n -Данные карты: %s %s/%s %s \n", var10001) + "--------------------------------------------------------------------------\n";
    }

    public String CCBalanceChange(String card_id, int money, boolean delta, int balance) {
        Object[] var10001 = new Object[]{card_id, String.format("%s%d", delta ? "+" : "-", money), balance};
        return "-----------------------Изменение баланса кред. карты-----------------------\n" + String.format("Кред. карта ID: %s\n---измен. баланса: %s, остаток: %d\n", var10001) + "--------------------------------------------------------------------------\n";
    }
}
