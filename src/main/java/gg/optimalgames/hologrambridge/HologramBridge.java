/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Chubbyduck1
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package gg.optimalgames.hologrambridge;

import gg.optimalgames.hologrambridge.connector.impl.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Construct a new {@link HologramBridge} for your {@link JavaPlugin}
 */
public final class HologramBridge {

    private final JavaPlugin javaPlugin;
    private final boolean verbose;

    /**
     * Initialize the HologramBridge without logging
     *
     * @param javaPlugin The plugin that is connecting
     */
    public HologramBridge(final JavaPlugin javaPlugin) {
        this(javaPlugin, false);
    }

    /**
     * Initialize the hologram bridge
     *
     * @param javaPlugin The plugin that is connecting
     * @param verbose    Whether it should log to console
     */
    public HologramBridge(final JavaPlugin javaPlugin,
                          final boolean verbose) {
        this.javaPlugin = javaPlugin;
        this.verbose = verbose;

        HologramAPI.setJavaPlugin(this.javaPlugin);
        this.checkConnectors();
    }

    /**
     * Check and load the possible connectors
     */
    private void checkConnectors() {
        if (this.isEnabled("HolographicDisplays")) {
            this.log("Found HolographicDisplays Connector");

            HologramAPI.setConnector(new HolographicDisplaysImpl(this, this.javaPlugin));
        }

        if (this.isEnabled("CMI")) {
            this.log("Found CMI Connector");

            HologramAPI.setConnector(new CMIImpl());
        }

        if (this.isEnabled("Holograms")) {
            this.log("Found Holograms Connector");

            HologramAPI.setConnector(new HologramsImpl());
        }

        if (this.isEnabled("DecentHolograms")) {
            this.log("Found DecentHolograms Connector");

            HologramAPI.setConnector(new DecentImpl());
        }

        if (this.isEnabled("FancyHolograms")) {
            this.log("Found FancyHolograms Connector");

            HologramAPI.setConnector(new FancyHologramsImpl());
        }
    }

    /**
     * Checks if a plugin is enabled
     *
     * @param plugin The plugin name to check
     * @return The {@link Boolean} value of if its enabled
     */
    private boolean isEnabled(String plugin) {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(plugin);
    }

    /**
     * Check if verbose is enabled, and log to console
     *
     * @param text The {@link String} to log
     */
    public void log(final String text) {
        if (!verbose) {
            return;
        }

        this.javaPlugin.getLogger().info(text);
    }

}
