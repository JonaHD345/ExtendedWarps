package de.jonahd345.extendedwarps.config;

import lombok.Getter;

@Getter
public enum ConfigType {
    CONFIG(""),
    MESSAGE("MSG_");

    private final String prefix;

    ConfigType(String prefix) {
        this.prefix = prefix;
    }
}
