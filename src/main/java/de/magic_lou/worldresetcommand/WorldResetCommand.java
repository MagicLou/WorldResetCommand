package de.magic_lou.worldresetcommand;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;

public final class WorldResetCommand extends JavaPlugin {



    public static WorldResetCommand instance;

    @Override
    public void onLoad() {

        //creat config 'isReset' if not exist
        saveConfig();
        if (!getConfig().contains("isReset")) {
            getConfig().set("isReset", false);
            saveConfig();
            return;
        }

        //reset world-folder if 'isReset'
        if (getConfig().getBoolean("isReset")) {
            try {
                File world = new File(Bukkit.getWorldContainer(), "world");
                File nether = new File(Bukkit.getWorldContainer(), "world_nether");
                File the_end = new File(Bukkit.getWorldContainer(), "world_the_end");

                FileUtils.deleteDirectory(world);
                FileUtils.deleteDirectory(nether);
                FileUtils.deleteDirectory(the_end);

                world.mkdirs();
                nether.mkdirs();
                the_end.mkdirs();

                new File(world, "data").mkdirs();
                new File(world, "datapacks").mkdirs();
                new File(world, "playerdata").mkdirs();
                new File(world, "poi").mkdirs();
                new File(world, "region").mkdirs();

                new File(nether, "data").mkdirs();
                new File(nether, "datapacks").mkdirs();
                new File(nether, "playerdata").mkdirs();
                new File(nether, "poi").mkdirs();
                new File(nether, "region").mkdirs();

                new File(the_end, "data").mkdirs();
                new File(the_end, "datapacks").mkdirs();
                new File(the_end, "playerdata").mkdirs();
                new File(the_end, "poi").mkdirs();
                new File(the_end, "region").mkdirs();


            } catch (IOException e) {
                e.printStackTrace();
            }

            getConfig().set("isReset", false);
            saveConfig();
        }
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginCommand("reset").setExecutor(new ResetCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
