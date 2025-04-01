package de.jonahd345.extendedwarps.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class LocationSerializer {
    public static String getDataFromLocation(Location loc) {
        return loc != null ? loc.getX() + ";" + loc.getY() + ";" + loc.getZ() + ";" + loc.getYaw() + ";" + loc.getPitch() + ";" + loc.getWorld().getUID() : "";
    }

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
