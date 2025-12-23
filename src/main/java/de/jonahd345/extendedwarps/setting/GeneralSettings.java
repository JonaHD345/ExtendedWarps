package de.jonahd345.extendedwarps.setting;

import de.jonahd345.developerutils.setting.SettingsSection;
import de.jonahd345.developerutils.setting.annotation.SettingsKey;
import lombok.Getter;

@Getter
public class GeneralSettings implements SettingsSection {
    @Override
    public String settingsPrefix() {
        return "config";
    }

    @SettingsKey(value = "update_notification", def = "true")
    private boolean updateNotification;

    @SettingsKey(value = "warp_sound", def = "true")
    private boolean warpSound;

    @SettingsKey(value = "warp_sound_name", def = "ENTITY_ENDERMAN_TELEPORT")
    private String warpSoundName;
}
