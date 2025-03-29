package de.jonahd345.extendedwarps.service;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import de.jonahd345.extendedwarps.util.FileUtil;
import de.jonahd345.extendedwarps.util.StringUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigService {
    private ExtendedWarps plugin;

    private File file;

    private FileConfiguration yamlConfiguration;

    public ConfigService(ExtendedWarps plugin) {
        this.plugin = plugin;
        this.file = new File("plugins/" + plugin.getName() + "/config.yml");
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public void loadConfig() {
        yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        boolean hasFileChanges = false;

        if (!file.exists()) {
            FileUtil.saveFile(yamlConfiguration, file);
        }
        for (Config config : Config.values()) {
            String configPath = config.getConfigType().name().toLowerCase() + "." + config.name().replace(config.getConfigType().getPrefix(), "").toLowerCase();
            Object value = yamlConfiguration.get(configPath);

            if (value == null) {
                yamlConfiguration.set(configPath, config.getDefaultValue());
                hasFileChanges = true;
                config.setValue(config.getDefaultValue() instanceof String ? StringUtil.translateColorCodes(config.getDefaultValue().toString()) : config.getDefaultValue());
                continue;
            }
            config.setValue(value instanceof String ? StringUtil.translateColorCodes(value.toString()) : value);
        }
        if (hasFileChanges) {
            FileUtil.saveFile(yamlConfiguration, file);
        }
    }
}
