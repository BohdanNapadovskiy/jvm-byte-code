package ru.napadovskiu.storage;

import ru.napadovskiu.models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryStorage implements Storage<User> {

    private final Map<Integer, User> userList;

    public MemoryStorage() {
        userList = new ConcurrentHashMap<>();
    }


    @Override
    public Integer add(User user) {
        if (user.getUserId() == null) {
            user.setUserId(this.userList.size() + 1);
        }
        this.userList.put(user.getUserId(), user);

        return userList.get(user.getUserId()).getUserId();
    }



    @Override
    public User get(Integer id) {
        return userList.get(id);
    }
}
