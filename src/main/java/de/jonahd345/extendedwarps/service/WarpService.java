package de.jonahd345.extendedwarps.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.model.Warp;
import de.jonahd345.extendedwarps.util.FileUtil;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class WarpService {
    private ExtendedWarps plugin;

    private ObjectMapper objectMapper;

    @Getter
    private List<Warp> warps;

    public WarpService(ExtendedWarps plugin) {
        this.plugin = plugin;
        this.objectMapper = new ObjectMapper();
        this.warps = new ArrayList<>();
    }

    public void loadWarps() {
        File file = new File(plugin.getDataFolder(), "warps.json");

        if (FileUtil.createFile(file)) {
            return;
        }
        try {
            warps = objectMapper.readValue(file, new TypeReference<>(){});
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE,"An error occurred", e);
        }
    }

    public void saveWarps() {
        try {
            objectMapper.writeValue(new File("plugins/" + plugin.getName() + "/warps.json"), warps);
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE,"An error occurred", e);
        }
    }
}
