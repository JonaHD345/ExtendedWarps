package de.jonahd345.extendedwarps.listener;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectionListener implements Listener {
    private ExtendedWarps plugin;

    public ConnectionListener(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (plugin.getUpdateService().isUpdateAvailable() && Config.UPDATE_NOTIFICATION.getValueAsBoolean()) {
            if (player.hasPermission("extendedwarps.admin")) {
                player.sendMessage(Config.MSG_PREFIX + "§7The new Version from §a§lExtendedWarps §7v§a" +
                        plugin.getUpdateService().getSpigotVersion().replace(".", "§7.§a") +
                        " §7is available at§8: §2https://www.spigotmc.org/resources/extendedwarps.123828/");
            }
        }
    }
}
