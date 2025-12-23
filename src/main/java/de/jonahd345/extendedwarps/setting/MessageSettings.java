package de.jonahd345.extendedwarps.setting;

import de.jonahd345.developerutils.setting.SettingsSection;
import de.jonahd345.developerutils.setting.annotation.SettingsKey;
import de.jonahd345.extendedwarps.util.StringUtil;
import lombok.Getter;

@Getter
public class MessageSettings implements SettingsSection {
    @Override
    public String settingsPrefix() {
        return "message";
    }

    @SettingsKey(value = "prefix", def = "&a&lEXTENDEDWARPS §8» ")
    private String prefix;

    @SettingsKey(value = "no_permission", def = "&cNo permission!")
    private String noPermission;

    @SettingsKey(value = "warp_teleport", def = "&7You have been teleported to &a%warp%&7.")
    private String warpTeleport;

    @SettingsKey(value = "set_warp", def = "&7You have been set the warp &a%warp%&7.")
    private String setWarp;

    @SettingsKey(value = "del_warp", def = "&7You have been delete the warp &a%warp%&7.")
    private String delWarp;

    @SettingsKey(value = "warp_already_existing", def = "&7The warp &a%warp%&7 already exists!")
    private String warpAlreadyExisting;

    @SettingsKey(value = "warp_is_not_existing", def = "&7The warp &a%warp%&7 is not existing!")
    private String warpIsNotExisting;

    @SettingsKey(value = "warp_command_usage", def = "&7Usage /warp <name>")
    private String warpCommandUsage;

    @SettingsKey(value = "set_warp_command_usage", def = "&7Usage /setwarp <name>")
    private String setWarpCommandUsage;

    @SettingsKey(value = "del_warp_command_usage", def = "&7Usage /delwarp <name>")
    private String delWarpCommandUsage;

    public String prefix() {
        return StringUtil.translateColorCodes(getPrefix());
    }

    public String noPermission() {
        return StringUtil.translateColorCodes(getPrefix() + getNoPermission());
    }

    public String warpTeleport() {
        return StringUtil.translateColorCodes(getPrefix() + getWarpTeleport());
    }

    public String setWarp() {
        return StringUtil.translateColorCodes(getPrefix() + getSetWarp());
    }

    public String delWarp() {
        return StringUtil.translateColorCodes(getPrefix() + getDelWarp());
    }

    public String warpAlreadyExisting() {
        return StringUtil.translateColorCodes(getPrefix() + getWarpAlreadyExisting());
    }

    public String warpIsNotExisting() {
        return StringUtil.translateColorCodes(getPrefix() + getWarpIsNotExisting());
    }

    public String warpCommandUsage() {
        return StringUtil.translateColorCodes(getPrefix() + getWarpCommandUsage());
    }

    public String setWarpCommandUsage() {
        return StringUtil.translateColorCodes(getPrefix() + getSetWarpCommandUsage());
    }

    public String delWarpCommandUsage() {
        return StringUtil.translateColorCodes(getPrefix() + getDelWarpCommandUsage());
    }
}
