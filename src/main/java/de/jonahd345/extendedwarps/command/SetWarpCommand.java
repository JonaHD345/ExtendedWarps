package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import de.jonahd345.extendedwarps.model.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {
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
            player.sendMessage(Config.getMessageWithPrefix(Config.MSG_NO_PERMISSION));
            return true;
        }
        if (args.length == 1) {
            if (plugin.getWarpService().getWarps().stream().anyMatch(w -> w.getName().equalsIgnoreCase(args[0]))) {
                player.sendMessage(Config.getMessageWithPrefix(Config.MSG_WARP_ALREADY_EXISTING).replace("%warp%", args[0]));
                return true;
            }
            plugin.getWarpService().getWarps().add(new Warp(args[0], player.getLocation()));
            player.sendMessage(Config.getMessageWithPrefix(Config.MSG_SET_WARP).replace("%warp%", args[0]));
        } else {
            player.sendMessage(Config.getMessageWithPrefix(Config.MSG_SET_WARP_COMMAND_USAGE));
        }
        return false;
    }
}
