package de.jonahd345.extendedwarps.util;

import com.google.gson.*;
import de.jonahd345.extendedwarps.model.Warp;
import org.bukkit.Location;

import java.lang.reflect.Type;

public class WarpAdapter implements JsonSerializer<Warp>, JsonDeserializer<Warp> {

    @Override
    public JsonElement serialize(Warp src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("name", src.getName());
        obj.addProperty("location", LocationSerializer.getDataFromLocation(src.getLocation()));
        return obj;
    }

    @Override
    public Warp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        String name = obj.get("name").getAsString();
        String locationData = obj.get("location").getAsString();
        Location loc = LocationSerializer.getLocationFromData(locationData);
        return new Warp(name, loc);
    }
}
