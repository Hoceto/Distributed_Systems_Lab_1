package user_service;

import date_src.UserDate;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.Map.Entry;

public final class UserConfig {
    private UserManagement manager;

    public UserConfig(UserManagement manager) {
        this.manager = manager;
    }

    public User registrateUser(int day, int month, int year, String name, String surname, String passid, String phone_num, String email, String TIN, int balance) {
        User user = new User();
        UserDate userdate = new UserDate(day, month, year);
        user.setBirthday_date(userdate);
        if (validateName(name)) {
            user.setUserName(name);
        }

        if (validateSurname(surname)) {
            user.setUserSurname(surname);
        }

        user.setUserPassportId(passid);
        user.setUserPhoneNumber(phone_num);
        user.setUserTin(TIN);
        user.setUserEmail(email);
        user.setUserBalance(balance);
        this.manager.addUser(user);
        return user;
    }

    public void removeUser(UUID id) {
        this.manager.removeUser(id);
    }

    public User getUser(UUID id) {
        return this.manager.getUser(id);
    }

    public static boolean validateName(String name) {
        if (name.matches("[A-Z][a-z]*")) {
            return true;
        } else {
            throw new IllegalArgumentException("Incorrect name format (must contain 1-inf a-z characters, first capital");
        }
    }

    public static boolean validateSurname(String surname) {
        if (surname.matches("[A-Z][a-z]*")) {
            return true;
        } else {
            throw new IllegalArgumentException("Incorrect surname format (must contain 1-inf a-z characters, first capital");
        }
    }

    public void changeBalance(UUID id, int diff) {
        User user = this.manager.getUser(id);
        user.setUserBalance(user.getUserBalance() + diff);
    }

    public void printUsersInfo() {
        Map<UUID, User> user_storage = this.manager.getUserStorage();
        int iter = 1;

        for(Iterator var3 = user_storage.entrySet().iterator(); var3.hasNext(); ++iter) {
            Entry<UUID, User> entry = (Entry)var3.next();
            User iter_user = (User)entry.getValue();
            System.out.println(String.format("\t%d. %s. Кредитных карт: %d, депозит: %s", iter, iter_user.getUserId(), iter_user.getCCList().size(), Objects.isNull(iter_user.getUserDepositId()) ? "отсутсвует" : "наличен"));
        }

        if (iter == 1) {
            System.out.println("Пользователи отсутствуют");
        }

    }
}
