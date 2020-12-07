package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.Transmission;

import java.lang.reflect.Type;

public class TransmissionSerializer implements JsonSerializer<Transmission> {

    /**
     *
     * @param transmission
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Transmission transmission, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("transId", transmission.getTransId());
        result.addProperty("transName", transmission.getTransName());
        return result;

    }
}
