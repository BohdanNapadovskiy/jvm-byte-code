package ru.napadovskiu.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.napadovskiu.models.User;

@Component
public class UserStorage implements Storage<User> {

    private final Storage storage;


    @Autowired
    public UserStorage(final Storage storage) {
        this.storage = storage;
    }


    @Override
    public Integer add(User user) {
        storage.add(user);
        User newUser = (User) storage.get(user.getUserId());

        return newUser.getUserId();
    }

    @Override
    public User get(Integer id) {
        return storage.get(id);
    }
}
