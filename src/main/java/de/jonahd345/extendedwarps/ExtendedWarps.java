package de.jonahd345.extendedwarps;

import de.jonahd345.developerutils.setting.SettingsManager;
import de.jonahd345.developerutils.setting.storage.YamlStorage;
import de.jonahd345.extendedwarps.command.DelWarpCommand;
import de.jonahd345.extendedwarps.command.ExtendedWarpsCommand;
import de.jonahd345.extendedwarps.command.SetWarpCommand;
import de.jonahd345.extendedwarps.command.WarpCommand;
import de.jonahd345.extendedwarps.listener.ConnectionListener;
import de.jonahd345.extendedwarps.service.UpdateService;
import de.jonahd345.extendedwarps.service.WarpService;
import de.jonahd345.extendedwarps.setting.GeneralSettings;
import de.jonahd345.extendedwarps.setting.MessageSettings;
import de.jonahd345.extendedwarps.util.Metrics;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public final class ExtendedWarps extends JavaPlugin {
    private SettingsManager settingsManager;
    private GeneralSettings generalSettings;
    private MessageSettings messageSettings;

    private WarpService warpService;

    private UpdateService updateService;

    @Override
    public void onEnable() {
        // Metrics
        new Metrics(this, 25309);

        // Settings
        settingsManager = new SettingsManager(new YamlStorage())
                .register(new GeneralSettings()).register(new MessageSettings());
        loadSettings();

        // Warps
        warpService = new WarpService(this);
        warpService.loadWarps();

        // Update Service
        updateService = new UpdateService(this);

        // Register Commands & Listeners
        init();
    }

    @Override
    public void onDisable() {
        warpService.saveWarps();
    }

    public static ExtendedWarps getInstance() {
        return getPlugin(ExtendedWarps.class);
    }

    public GeneralSettings getGeneralSettings() {
        if (generalSettings == null) {
            generalSettings = settingsManager.find(GeneralSettings.class).orElse(null);
        }
        return generalSettings;
    }

    public MessageSettings getMessageSettings() {
        if (messageSettings == null) {
            messageSettings = settingsManager.find(MessageSettings.class).orElse(null);
        }
        return messageSettings;
    }

    public void reloadSettings() {
        generalSettings = null;
        messageSettings = null;
        loadSettings();
    }

    private void loadSettings() {
        try {
            settingsManager.load(new File("plugins/" + getName() + "/config.yml").toPath());
        } catch (Exception e) {
            getLogger().severe("Could not load config.yml:\n" + e.getMessage());
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    private void init() {
        PluginManager pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new ConnectionListener(this), this);

        getCommand("warp").setExecutor(new WarpCommand(this));
        getCommand("setwarp").setExecutor(new SetWarpCommand(this));
        getCommand("delwarp").setExecutor(new DelWarpCommand(this));
        getCommand("extendedwarps").setExecutor(new ExtendedWarpsCommand(this));
    }
}
