package de.jonahd345.extendedwarps.config;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Config {
    WARP_SOUND(ConfigType.CONFIG, true),
    WARP_SOUND_NAME(ConfigType.CONFIG, "ENTITY_ENDERMAN_TELEPORT"),
    MSG_PREFIX(ConfigType.MESSAGE, "&a&lEXTENDEDWARPS §8» "),
    MSG_NO_PERMISSION(ConfigType.MESSAGE, "&cNo permission!"),
    MSG_WARP_TELEPORT(ConfigType.MESSAGE, "&7You have been teleported to &a%warp%&7."),
    MSG_SET_WARP(ConfigType.MESSAGE, "&7You have set the warp &a%warp%&7."),
    MSG_DEL_WARP(ConfigType.MESSAGE, "&7You have delete the warp &a%warp%&7."),
    MSG_WARP_ALREADY_EXISTING(ConfigType.MESSAGE, "&7The warp &a%warp%&7 already exists!"),
    MSG_WARP_IS_NOT_EXISTING(ConfigType.MESSAGE, "&7The warp &a%warp%&7 is not existing!"),
    MSG_WARP_COMMAND_USAGE(ConfigType.MESSAGE, "&7Usage /warp <name>"),
    MSG_SET_WARP_COMMAND_USAGE(ConfigType.MESSAGE, "&7Usage /setwarp <name>"),
    MSG_DEL_WARP_COMMAND_USAGE(ConfigType.MESSAGE, "&7Usage /delwarp <name>");

    private final ConfigType configType;
    private final Object defaultValue;
    @Setter
    private Object value;

    Config(ConfigType configType, Object defaultValue) {
        this.configType = configType;
        this.defaultValue = defaultValue;
    }

    public static String getMessageWithPrefix(Config message) {
        return MSG_PREFIX + message.getValue().toString();
    }

    public Boolean getValueAsBoolean() {
        return Boolean.valueOf(this.value.toString());
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
