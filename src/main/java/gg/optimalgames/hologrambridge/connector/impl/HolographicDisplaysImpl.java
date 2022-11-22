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

package gg.optimalgames.hologrambridge.connector.impl;

import gg.optimalgames.hologrambridge.HologramBridge;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.connector.impl.holographic.Holographic2;
import gg.optimalgames.hologrambridge.connector.impl.holographic.Holographic3;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import gg.optimalgames.hologrambridge.lines.Line;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class HolographicDisplaysImpl implements Connector {

    private final Connector connector;

    public HolographicDisplaysImpl(final HologramBridge hologramBridge, final JavaPlugin javaPlugin) {
        if (this.isModern()) {
            hologramBridge.log("Using HolographicDisplays 3.x");
            this.connector = new Holographic3(javaPlugin);
            return;
        }

        hologramBridge.log("Using HolographicDisplays 2.x");
        this.connector = new Holographic2();
    }

    @Override
    public Hologram createHologram(final Location location) {
        return this.connector.createHologram(location);
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        this.connector.setLine(hologram, lineIndex, line);
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        this.connector.updateLine(hologram, lineIndex, line);
    }

    @Override
    public void appendLine(final Hologram hologram,
                           final Line line) {
        this.connector.appendLine(hologram, line);
    }

    @Override
    public void clearLines(final Hologram hologram) {
        this.connector.clearLines(hologram);
    }

    @Override
    public void teleport(final Hologram hologram,
                         final Location location) {
        this.connector.teleport(hologram, location);
    }

    @Override
    public void delete(final Hologram hologram) {
        this.connector.delete(hologram);
    }

    @Override
    public void setVisibleByDefault(final VisibilityManager visibilityManager,
                                    final boolean visibleByDefault) {
        this.connector.setVisibleByDefault(visibilityManager, visibleByDefault);
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager,
                       final Player player) {
        this.connector.showTo(visibilityManager, player);
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager,
                       final Player player) {
        this.connector.hideTo(visibilityManager, player);
    }

    /**
     * Check if the HolographicDisplays version is below 3.0
     *
     * @return true if the version is below 3.0
     */
    private boolean isModern() {
        try {
            Class.forName("me.filoghost.holographicdisplays.api.HolographicDisplaysAPI");
            return true;
        } catch (final ClassNotFoundException e) {
            return false;
        }
    }

}
