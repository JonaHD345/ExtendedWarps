package de.jonahd345.extendedwarps.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@Getter
public class Warp {
    @Setter
    private String name;

    private Location location;

    public Warp() {
        // Default constructor required for Gson
    }

    public Warp(String name, Location location) {
        this.name = name;
        this.location = location;
    }
}