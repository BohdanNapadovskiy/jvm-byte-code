package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.Engine;

import java.lang.reflect.Type;

public class EngineSerializer implements JsonSerializer<Engine> {


    /**
     *
     * @param engine
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Engine engine, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject result = new JsonObject();
        result.addProperty("engineId", engine.getEngineId());
        result.addProperty("engineName", engine.getEngineName());
        return result;

    }
}
