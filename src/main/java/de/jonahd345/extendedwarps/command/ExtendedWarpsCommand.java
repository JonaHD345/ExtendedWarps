package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
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
            sender.sendMessage(plugin.getMessageSettings().noPermission());
            return true;
        }
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(plugin.getMessageSettings().prefix() + "§7" + plugin.getDescription().getName() + " Help:");
                sender.sendMessage(plugin.getMessageSettings().warpCommandUsage());
                sender.sendMessage(plugin.getMessageSettings().setWarpCommandUsage());
                sender.sendMessage(plugin.getMessageSettings().delWarpCommandUsage());
            } else if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadSettings();
                sender.sendMessage(plugin.getMessageSettings().prefix() + "§7Config reloaded!");
            }
        } else {
            sender.sendMessage(plugin.getMessageSettings().prefix() + "§7" + plugin.getDescription().getName() + " Help:");
            sender.sendMessage(plugin.getMessageSettings().warpCommandUsage());
            sender.sendMessage(plugin.getMessageSettings().setWarpCommandUsage());
            sender.sendMessage(plugin.getMessageSettings().delWarpCommandUsage());
        }
        return false;
    }
}
