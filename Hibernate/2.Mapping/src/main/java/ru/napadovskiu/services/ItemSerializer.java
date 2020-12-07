package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.Item;

import java.lang.reflect.Type;

public class ItemSerializer implements JsonSerializer<Item> {

    /**
     *
     * @param item
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Item item, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("itemId", item.getItemId());
        result.addProperty("description", item.getDescription());
        result.addProperty("car", item.getCar().getCarName());
        result.addProperty("closed", item.isClosed());

        result.addProperty("transName", item.getCar().getTransmission().getTransName());
        result.addProperty("engineName", item.getCar().getEngine().getEngineName());
        result.addProperty("gearBoxName", item.getCar().getGearBox().getGearBoxName());

        result.addProperty("userId",item.getUser().getUserId());


        result.addProperty("user", item.getUser().getUserName());
        result.addProperty("date", String.valueOf(item.getDate()));
        return result;

    }
}
