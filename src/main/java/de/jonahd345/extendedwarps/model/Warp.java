package de.jonahd345.extendedwarps.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import de.jonahd345.extendedwarps.util.LocationSerializer;
import lombok.Data;
import org.bukkit.Location;

@Data
public class Warp {
    private final String name;

    private final Location location;

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @JsonGetter("location")
    public String getSerializedLocation() {
        return LocationSerializer.getDataFromLocation(location);
    }

    @JsonSetter("location")
    public Location getSerializedLocation(String locationString) {
        return LocationSerializer.getLocationFromData(locationString);
    }
}