package de.jonahd345.extendedwarps.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import de.jonahd345.extendedwarps.util.LocationSerializer;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
public class Warp {
    @Setter
    private String name;

    private Location location;

    public Warp() {
        // Default constructor required for Jackson
    }

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    @JsonGetter("location")
    public String getSerializedLocation() {
        return LocationSerializer.getDataFromLocation(location);
    }

    @JsonSetter("location")
    public void setSerializedLocation(String locationString) {
        this.location = LocationSerializer.getLocationFromData(locationString);
    }
}