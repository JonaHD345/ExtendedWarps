package de.jonahd345.extendedwarps.util;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtil {
    /**
     * Saves the provided FileConfiguration to the specified file.
     *
     * @param fileConfiguration the FileConfiguration to save
     * @param file the file to save the YamlConfiguration to
     */
    public static void saveFile(FileConfiguration fileConfiguration, File file) {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the specified file if it does not exist.
     *
     * @param file the file to create
     */
    public static void createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates the specified directory if it does not exist.
     *
     * @param file the directory to create
     */
    public static void createDirectory(File file) {
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }
}
