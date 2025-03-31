package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import de.jonahd345.extendedwarps.model.Warp;
import de.jonahd345.extendedwarps.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DelWarpCommand implements CommandExecutor, TabCompleter {
    private ExtendedWarps plugin;

    public DelWarpCommand(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!sender.hasPermission("extendedwarps.command.delwarp")) {
            sender.sendMessage(Config.getMessageWithPrefix(Config.MSG_NO_PERMISSION));
            return true;
        }
        if (args.length == 1) {
            Warp warp = plugin.getWarpService().getWarps().stream().filter(w -> w.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);

            if (warp == null) {
                sender.sendMessage(Config.getMessageWithPrefix(Config.MSG_WARP_IS_NOT_EXISTING));
                return true;
            }
            plugin.getWarpService().getWarps().remove(warp);
            sender.sendMessage(StringUtil.replacePlaceholder(Config.getMessageWithPrefix(Config.MSG_DEL_WARP), Map.of("%warp%", warp.getName())));
            return true;
        } else {
            sender.sendMessage(Config.getMessageWithPrefix(Config.MSG_DEL_WARP_COMMAND_USAGE));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> subcommand = new ArrayList<>();

        if (sender.hasPermission("extendedwarps.command.delwarp") || sender.hasPermission("extendedwarps.admin")) {
            if (args.length == 1) {
                for (Warp warp : plugin.getWarpService().getWarps()) {
                    subcommand.add(warp.getName());
                }
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
