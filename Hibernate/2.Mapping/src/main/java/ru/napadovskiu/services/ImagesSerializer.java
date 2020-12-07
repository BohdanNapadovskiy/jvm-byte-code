package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.Images;

import java.lang.reflect.Type;

public class ImagesSerializer implements JsonSerializer<Images> {


    /**
     *
     * @param images
     * @param type
     * @param jsonSerializationContext
     * @return
     */

    @Override
    public JsonElement serialize(Images images, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("imageId", images.getImageId());
        result.addProperty("imagePath", images.getImagePath());
        return result;

    }
}
