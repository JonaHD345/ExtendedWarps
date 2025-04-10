package de.jonahd345.extendedwarps.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.model.Warp;
import de.jonahd345.extendedwarps.util.FileUtil;
import de.jonahd345.extendedwarps.util.LocationSerializer;
import de.jonahd345.extendedwarps.util.WarpAdapter;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class WarpService {
    private ExtendedWarps plugin;

    private Gson gson;

    @Getter
    private List<Warp> warps;

    public WarpService(ExtendedWarps plugin) {
        this.plugin = plugin;
        this.gson = new GsonBuilder().registerTypeAdapter(Warp.class, new WarpAdapter()).create();
        this.warps = new ArrayList<>();
    }

    public void loadWarps() {
        File file = new File(plugin.getDataFolder(), "warps.json");

        if (FileUtil.createFile(file)) {
            return;
        }
        try (FileReader reader = new FileReader(file)) {
            warps = gson.fromJson(reader, new TypeToken<List<Warp>>(){}.getType());
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE,"An error occurred", e);
        }
    }

    public void saveWarps() {
        try (FileWriter writer = new FileWriter("plugins/" + plugin.getName() + "/warps.json"))  {
            gson.toJson(warps, new TypeToken<List<Warp>>(){}.getType(), writer);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE,"An error occurred", e);
        }
    }
}
