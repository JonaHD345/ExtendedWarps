package de.jonahd345.extendedwarps.command;

import de.jonahd345.extendedwarps.ExtendedWarps;
import de.jonahd345.extendedwarps.config.Config;
import de.jonahd345.extendedwarps.model.Warp;
import de.jonahd345.extendedwarps.util.StringUtil;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WarpCommand implements CommandExecutor, TabCompleter {
    private ExtendedWarps plugin;

    public WarpCommand(ExtendedWarps plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("extendedwarps.command.warp")) {
            player.sendMessage(Config.getMessageWithPrefix(Config.MSG_NO_PERMISSION));
            return true;
        }
        if (args.length == 1) {
            Warp warp = plugin.getWarpService().getWarps().stream().filter(w -> w.getName().equalsIgnoreCase(args[0])).findFirst().orElse(null);
            Sound sound = Registry.SOUNDS.get(NamespacedKey.fromString(Config.WARP_SOUND_NAME.toString()));

            if (sound == null) {
                sound = Sound.ENTITY_ENDERMAN_TELEPORT;
            }
            if (warp == null) {
                player.sendMessage(StringUtil.replacePlaceholder(Config.getMessageWithPrefix(Config.MSG_WARP_IS_NOT_EXISTING), Map.of("%warp%", args[0])));
                return true;
            }
            if (warp.getLocation() == null) {
                player.sendMessage(Config.MSG_PREFIX + "§cWarp location is not set.");
                return true;
            }
            player.teleport(warp.getLocation());
            player.playSound(player.getLocation(), sound, 1.0F, 1.0F);
            player.sendMessage(StringUtil.replacePlaceholder(Config.getMessageWithPrefix(Config.MSG_WARP_TELEPORT), Map.of("%warp%", args[0])));
        } else {
            player.sendMessage(Config.getMessageWithPrefix(Config.MSG_WARP_COMMAND_USAGE));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        ArrayList<String> subcommand = new ArrayList<>();

        if (sender.hasPermission("extendedwarps.command.warp") || sender.hasPermission("extendedwarps.admin")) {
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
