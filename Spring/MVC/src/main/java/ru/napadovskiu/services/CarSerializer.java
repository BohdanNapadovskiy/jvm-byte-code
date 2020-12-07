package ru.napadovskiu.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.napadovskiu.entities.Car;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {

    /**
     *
     * @param car
     * @param type
     * @param jsonSerializationContext
     * @return
     */
    @Override
    public JsonElement serialize(Car car, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("carId", car.getCarId());
        result.addProperty("carName", car.getCarName());
        result.addProperty("engine", car.getEngine().getEngineName());
        result.addProperty("gearBox", car.getGearBox().getGearBoxName());
        result.addProperty("images", car.getImages().getImagePath());
        result.addProperty("transmission", car.getTransmission().getTransName());
        return result;

    }
}
