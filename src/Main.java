import main_service.MainService;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public final class Main {
    public Main() {
    }

    public static void main(String[] args) {
        System.out.println("Обычное рабочее утро. НашБанк начинает работу.\nКак и любой банк, он имеет свой большой денежный счет и своих клиентов(которые в идеале приносят ему еще денежек).\nКаждый клиент имеет свой депозитный счет и определнный набор кредитных карт.\nС каждым из этих атрибутов можно совершать операции, за которые отвечают соотв. операционисты.\nНаша банковая система состоит из таких сервисов:\n•Главный сервис, обрабатывающий все составляющие;\n\t •Сервис конфигурации пользователей (регистрация и модификация данных);\n\t •Сервис контроля проведения операций с деп. счетами и кредитными картами;\n\t •Сервис контроля выполнения работы операций (за успешное выполнение работник получает з.п.)\n\t •Сервис выдачи информации об изменениях состоянии системы;");
        MainService mainService = new MainService();
        System.out.println("Для начала внесем жизнь и смысл существования банка и добавим несколько клиентов посредством соотв. сервиса:");
        UUID uid1 = mainService.addUser(12, 4, 1998, "Mike", "Pence", "06152", "+3801223221", "foo01@gmail.com", "6718209381", 50000);
        UUID uid2 = mainService.addUser(24, 11, 1965, "Donald", "Trump", "61233", "+3801231612", "foo02@gmail.com", "6771209381", 3022);
        UUID uid3 = mainService.addUser(1, 1, 2019, "Vlad", "Dracula", "84238", "+3801231661", "foo03@gmail.com", "1121209381", 424242);
        System.out.println("Не забываем, что для выполнение работы (в идеале) нужны рабочие. Добавим же их!");
        UUID workId1 = mainService.addWorker("Bob", "Banks", 1);
        UUID workId2 = mainService.addWorker("Chou", "Ming", 1);
        System.out.println("Один из пользователей решил завести кредитную карту, сразу же положить на нее деньги и снять с нее:");
        UUID card1 = mainService.openCC(workId1, uid1, "312512331", 2, 2020);
        mainService.putMoneyCC(workId1, card1, uid1, 200);
        mainService.withdrawCC(workId2, card1, uid1, 150);
        System.out.println("Другой же пользователь решил открыть депозит и закрыть его, переместившись во времени на 4 месяца:");
        Calendar today = Calendar.getInstance();
        today.set(11, 0);
        mainService.openDeposit(workId2, uid2, today.getTime(), 1000);
        today.add(2, 4);
        mainService.withdrawDeposit(workId2, uid2, today.getTime());
        System.out.println("Второй пользователь после перемещения во времени решил отречься от членства в нашем банке");
        mainService.removeUser(uid2);
        System.out.println("Время пролетело незаметно. Самое время подвести итоги:");
        mainService.printSummary();
    }
}
