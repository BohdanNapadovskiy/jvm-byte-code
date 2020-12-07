package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.GearBox;

import java.lang.reflect.Type;

public class GearBoxSerializer implements JsonSerializer<GearBox> {


    /**
     *
     * @param gearBox
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(GearBox gearBox, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();
        result.addProperty("gearBoxId", gearBox.getGearBoxId());
        result.addProperty("gearBoxName",gearBox.getGearBoxName());
        return result;

    }
}

