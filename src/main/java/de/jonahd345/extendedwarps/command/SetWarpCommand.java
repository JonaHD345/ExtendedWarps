package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.model.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class SetWarpCommand implements CommandExecutor, TabCompleter {
    private ExtendedWarps plugin;

    public SetWarpCommand(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("extendedwarps.command.setwarp")) {
            player.sendMessage(plugin.getMessageSettings().noPermission());
            return true;
        }
        if (args.length == 1) {
            String warpName = args[0].trim();

            if (plugin.getWarpService().getWarps().stream().anyMatch(w -> w.getName().equalsIgnoreCase(warpName))) {
                player.sendMessage(plugin.getMessageSettings().warpAlreadyExisting().replace("%warp%", warpName));
                return true;
            }
            plugin.getWarpService().getWarps().add(new Warp(warpName, player.getLocation()));
            player.sendMessage(plugin.getMessageSettings().setWarp().replace("%warp%", warpName));
        } else {
            player.sendMessage(plugin.getMessageSettings().setWarpCommandUsage());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return List.of();
    }
}
