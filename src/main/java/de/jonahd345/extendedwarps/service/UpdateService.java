package de.jonahd345.extendedwarps.service;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import de.jonahd345.xenfororesourcemanagerapi.XenforoResourceManagerAPI;
import de.jonahd345.xenfororesourcemanagerapi.model.Resource;
import lombok.Getter;

import java.util.Arrays;

public class UpdateService {
    private ExtendedWarps plugin;

    private XenforoResourceManagerAPI xenforoResourceManagerAPI;

    private String  pluginVersion;

    @Getter
    private String spigotVersion;

    @Getter
    private boolean updateAvailable;

    public UpdateService(ExtendedWarps plugin) {
        this.plugin = plugin;
        this.xenforoResourceManagerAPI = new XenforoResourceManagerAPI();
        this.pluginVersion = this.plugin.getDescription().getVersion();
        this.updateAvailable = false;
        this.checkForUpdate();
    }

    public void checkForUpdate() {
        Resource resource = this.xenforoResourceManagerAPI.getResource(123828);

        if (resource != null) {
            this.spigotVersion = resource.getCurrentVersion();
        } else {
            this.spigotVersion = this.pluginVersion;
        }
        if (this.spigotVersion != null && !this.spigotVersion.isEmpty()) {
            this.updateAvailable = this.spigotIsNewer();
            if (this.updateAvailable && Config.UPDATE_NOTIFICATION.getValueAsBoolean()) {
                this.plugin.getLogger().info(Config.MSG_PREFIX + "§7The new Version from ExtendedWarps v" +
                        this.spigotVersion + " is available at: https://www.spigotmc.org/resources/extendedwarps.123828/");
            }
        }
    }

    private boolean spigotIsNewer() {
        if (this.spigotVersion != null && !this.spigotVersion.isEmpty()) {
            int[] plV = this.toReadable(this.pluginVersion);
            int[] spV = this.toReadable(this.spigotVersion);
            if (plV[0] < spV[0]) {
                return true;
            } else {
                return (plV[1] < spV[1]);
            }
        } else {
            return false;
        }
    }

    private int[] toReadable(String version) {
        return Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray();
    }
}
