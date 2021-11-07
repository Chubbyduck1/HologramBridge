/*
 * MIT License
 *
 * Copyright (c) 2021 Chubbyduck1
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package me.chubbyduck.holobridge;

import lombok.Getter;
import me.chubbyduck.holobridge.impl.CMIImpl;
import me.chubbyduck.holobridge.impl.HologramsImpl;
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
     *
     * @param javaPlugin The plugin that is connecting
     */
    public HologramBridge(JavaPlugin javaPlugin) {
        this(javaPlugin, false);
    }

    /**
     * Initialize the hologram bridge
     *
     * @param javaPlugin The plugin that is connecting
     * @param verbose    Whether it should log to console
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
        if (isEnabled("HolographicDisplays")) {
            if (verbose) {
                Bukkit.getLogger().info("Found HolographicDisplays Connector");
            }

            HologramAPI.setConnector(new HolographicDisplaysImpl());
        }

        if (isEnabled("CMI")) {
            if (verbose) {
                Bukkit.getLogger().info("Found CMI Connector");
            }

            HologramAPI.setConnector(new CMIImpl());
        }

        if (isEnabled("Holograms")) {
            if (verbose) {
                Bukkit.getLogger().info("Found Holograms Connector");
            }

            HologramAPI.setConnector(new HologramsImpl());
        }
    }

    /**
     * Checks if a plugin is enabled
     *
     * @param plugin The plugin name to check
     * @return The {@link Boolean} value of if its enabled
     */
    public boolean isEnabled(String plugin) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(plugin);
    }

}
