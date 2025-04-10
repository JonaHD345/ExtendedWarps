package de.jonahd345.extendedwarps;

import de.jonahd345.extendedwarps.command.DelWarpCommand;
import de.jonahd345.extendedwarps.command.ExtendedWarpsCommand;
import de.jonahd345.extendedwarps.command.SetWarpCommand;
import de.jonahd345.extendedwarps.command.WarpCommand;
import de.jonahd345.extendedwarps.listener.ConnectionListener;
import de.jonahd345.extendedwarps.service.ConfigService;
import de.jonahd345.extendedwarps.service.UpdateService;
import de.jonahd345.extendedwarps.service.WarpService;
import de.jonahd345.extendedwarps.util.Metrics;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class ExtendedWarps extends JavaPlugin {
    private ConfigService configService;

    private WarpService warpService;

    private UpdateService updateService;

    @Override
    public void onEnable() {
        new Metrics(this, 25309);

        this.configService = new ConfigService(this);
        this.configService.loadConfig();

        this.warpService = new WarpService(this);
        this.warpService.loadWarps();

        this.updateService = new UpdateService(this);

        init();
    }

    @Override
    public void onDisable() {
        this.warpService.saveWarps();
    }

    public static ExtendedWarps getInstance() {
        return getPlugin(ExtendedWarps.class);
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
