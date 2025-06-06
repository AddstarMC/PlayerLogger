package me.mcluke300.playerlogger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.mcluke300.playerlogger.commands.PlayerLoggerCommand;
import me.mcluke300.playerlogger.config.*;
import me.mcluke300.playerlogger.listeners.PListener;
import me.mcluke300.playerlogger.mysql.MySQL;

import java.util.Objects;

public class PlayerLogger extends JavaPlugin {

    // Plugin
    public static PlayerLogger plugin;

    public MySQL getDataSource() {
        return dataSource;
    }

    private MySQL dataSource;

    private PListener playerListener;

    @Override
    public void onEnable() {
        plugin = this;

        Config.LoadConfiguration();
        Config.getValues();
        dataSource = new MySQL();
        dataSource.initialize();

        // Registering Listeners
        playerListener = new PListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(playerListener, this);

        // Commands
        // Command
        PlayerLoggerCommand executor = new PlayerLoggerCommand(this);
        Objects.requireNonNull(getCommand("playerlogger")).setExecutor(executor);
    }

    @Override
    public void onDisable() {
        if (playerListener != null) {
            playerListener.shutdown();
        }
    }

    public void DebugLog(String msg) {
        if (Config.Debug()) {
            System.out.println("[PlayerLogger] " + msg);
        }
    }

    public void Log(String msg) {
        System.out.println("[PlayerLogger] " + msg);
    }

    public PListener getPlayerListener() {
        return playerListener;
    }
}
