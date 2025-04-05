package de.jonahd345.extendedwarps.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

/**
 * This class provides methods for serialising and deserialising location objects.
 */
public class LocationSerializer {
    /**
     * Serializes a Location object into a string.
     *
     * @param loc the Location object to serialize
     * @return a string representation of the Location object
     */
    public static String getDataFromLocation(Location loc) {
        return loc != null ? loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch() + ";" + loc.getWorld().getUID() : "";
    }

    /**
     * Deserializes a string into a Location object.
     *
     * @param s the string representation of the Location object
     * @return the Location object, or null if the string is invalid
     */
    public static Location getLocationFromData(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        String[] parts = s.split(";");
        if (parts.length != 6) {
            return null;
        }
        UUID u = UUID.fromString(parts[5]);
        World w = Bukkit.getServer().getWorld(u);

        return new Location(w, Double.parseDouble(parts[0]), Double.parseDouble(parts[1]), Double.parseDouble(parts[2]), Float.parseFloat(parts[3]), Float.parseFloat(parts[4]));
    }
}
