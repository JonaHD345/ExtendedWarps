package de.jonahd345.extendedwarps.util;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * This class provides auxiliary methods for saving and creating files and directories.
 */
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
     * @return boolean, if the file was created it returns true
     */
    public static boolean createFile(File file) {
        if (!file.exists()) {
            try {
                file.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
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
