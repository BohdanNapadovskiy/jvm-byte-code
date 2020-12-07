package ru.napadovskiu.storage;

import ru.napadovskiu.models.User;

public interface Storage<T> {

    Object add(final T t);

    User get(final Integer id);


}
