package de.magic_lou.worldresetcommand;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ResetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        reset();
        return false;
    }

    public void reset() {
        for (Player player : Bukkit.getOnlinePlayers()) player.kick(Component.text("World Reset"));
        //tells the plugin to reset the world
        WorldResetCommand.instance.getConfig().set("isReset", true);
        WorldResetCommand.instance.saveConfig();
        //restarts Server
        Bukkit.spigot().restart();
    }

}
