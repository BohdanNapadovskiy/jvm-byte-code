package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.User;

import java.lang.reflect.Type;

public class UserSerializer implements JsonSerializer<User> {

    /**
     *
     * @param user
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("userId", user.getUserId());
        result.addProperty("userName", user.getUserName());
        result.addProperty("userPassword", user.getUserPassword());

        return result;
    }
}
