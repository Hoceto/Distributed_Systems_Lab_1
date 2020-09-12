package User_service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User_management {
    private Map<UUID, User> user_storage = new HashMap();

    public User_management() {
    }

    void addUser(User user) {
        this.user_storage.put(user.getUser_id(), user);
    }

    void removeUser(UUID uuid) {
        this.user_storage.remove(uuid);
    }

    User getUser(UUID uuid) {
        return (User)this.user_storage.get(uuid);
    }

    public Map<UUID, User> getUserStorage() {
        return this.user_storage;
    }
}