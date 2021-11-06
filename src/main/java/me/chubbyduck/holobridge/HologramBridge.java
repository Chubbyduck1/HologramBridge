package me.chubbyduck.holobridge;

import lombok.Getter;
import me.chubbyduck.holobridge.impl.CMIImpl;
import me.chubbyduck.holobridge.impl.HolographicDisplaysImpl;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramBridge {

    @Getter
    private static HologramBridge instance;

    @Getter
    private final JavaPlugin javaPlugin;

    @Getter
    private final boolean verbose;

    /**
     * Initialize the HologramBridge without logging
     * @param javaPlugin The plugin that is connecting
     */
    public HologramBridge(JavaPlugin javaPlugin) {
        this(javaPlugin, false);
    }

    /**
     * Initialize the hologram bridge
     * @param javaPlugin The plugin that is connecting
     * @param verbose Whether it should log to console
     */
    public HologramBridge(JavaPlugin javaPlugin, boolean verbose) {
        this.javaPlugin = javaPlugin;
        this.verbose = verbose;

        instance = this;

        checkConnectors();
    }

    /**
     * Check and load the possible connectors
     */
    public void checkConnectors() {
        if(isEnabled("HolographicDisplays")) {
            if(verbose) {
                Bukkit.getLogger().info("Found HolographicDisplays Connector");
            }

            HologramAPI.setConnector(new HolographicDisplaysImpl());
        }

        if(isEnabled("CMI")) {
            if(verbose) {
                Bukkit.getLogger().info("Found CMI Connector");
            }

            HologramAPI.setConnector(new CMIImpl());
        }
    }

    /**
     * Checks if a plugin is enabled
     * @param plugin The plugin name to check
     * @return The {@link Boolean} value of if its enabled
     */
    public boolean isEnabled(String plugin) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(plugin);
    }

}
