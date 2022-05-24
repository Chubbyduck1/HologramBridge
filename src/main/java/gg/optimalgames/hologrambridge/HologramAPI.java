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

package gg.optimalgames.hologrambridge;

import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A class which handles Hologram data for external plugins
 */
public class HologramAPI {
    
    private static Connector connector;
    private static JavaPlugin javaPlugin;

    public HologramAPI() {
        throw new UnsupportedOperationException("Initialization of a utility class");
    }

    /**
     * Create a hologram at a location
     *
     * @param location The {@link Location} for the hologram
     * @return The created Hologram
     */
    public static Hologram createHologram(final Location location) {
        return HologramAPI.connector.createHologram(location);
    }

    /**
     * Check if the HologramAPI has a connector
     *
     * @return The {@link Boolean} value of if a connector is found
     */
    public static boolean hasConnector() {
        return HologramAPI.connector != null;
    }

    /**
     * Get the current {@link Connector} source
     * @return The {@link Connector}
     */
    public static Connector getConnector() {
        return HologramAPI.connector;
    }

    /**
     * Set the {@link Connector} source
     * @param connector The {@link Connector} source
     */
    public static void setConnector(final Connector connector) {
        HologramAPI.connector = connector;
    }

    /**
     * Get the {@link JavaPlugin} used
     * @return The {@link JavaPlugin}
     */
    public static JavaPlugin getJavaPlugin() {
        return HologramAPI.javaPlugin;
    }

    /**
     * Set the current {@link JavaPlugin} instance
     * @param javaPlugin The {@link JavaPlugin}
     */
    public static void setJavaPlugin(final JavaPlugin javaPlugin) {
        HologramAPI.javaPlugin = javaPlugin;
    }

}
