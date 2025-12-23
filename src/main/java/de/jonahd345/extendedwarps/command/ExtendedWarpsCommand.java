package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.model.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ExtendedWarpsCommand implements CommandExecutor, TabCompleter {
    private ExtendedWarps plugin;

    public ExtendedWarpsCommand(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("extendedwarps.command.extendedwarps") || !sender.hasPermission("extendedwarps.admin")) {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> subcommand = new ArrayList<>();

        if (sender.hasPermission("extendedwarps.command.extendedwarps") || sender.hasPermission("extendedwarps.admin")) {
            if (args.length == 1) {
                subcommand.add("reload");
                subcommand.add("help");
            }
        }
        ArrayList<String> cl = new ArrayList<>();
        String currentarg = args[args.length - 1].toLowerCase();

        for(String s1 : subcommand) {
            String s2 = s1.toLowerCase();
            if(s2.startsWith(currentarg)) {
                cl.add(s1);
            }
        }
        return cl;
    }
}
