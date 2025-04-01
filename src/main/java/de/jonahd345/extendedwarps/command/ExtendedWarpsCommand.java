package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ExtendedWarpsCommand implements CommandExecutor {
    private ExtendedWarps plugin;

    public ExtendedWarpsCommand(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("extendedwarps.command.extendedwarps")) {
            sender.sendMessage(Config.getMessageWithPrefix(Config.MSG_NO_PERMISSION));
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(Config.MSG_PREFIX + "§" + plugin.getDescription().getName() + " Help:");
                sender.sendMessage(Config.MSG_PREFIX + "§7/warp <name> §8- §7Teleport to a warp.");
                sender.sendMessage(Config.MSG_PREFIX + "§7/setwarp <name> §8- §7Set a warp.");
                sender.sendMessage(Config.MSG_PREFIX + "§7/delwarp <name> §8- §7Delete a warp.");
            } else if (args[0].equalsIgnoreCase("reload")) {
                plugin.getConfigService().loadConfig();
                sender.sendMessage(Config.MSG_PREFIX + "§7Config reloaded!");
            }
        } else {
            sender.sendMessage(Config.MSG_PREFIX + "§" + plugin.getDescription().getName() + " Help:");
            sender.sendMessage(Config.MSG_PREFIX + "§7/warp <name> §8- §7Teleport to a warp.");
            sender.sendMessage(Config.MSG_PREFIX + "§7/setwarp <name> §8- §7Set a warp.");
            sender.sendMessage(Config.MSG_PREFIX + "§7/delwarp <name> §8- §7Delete a warp.");
        }
        return false;
    }
}
