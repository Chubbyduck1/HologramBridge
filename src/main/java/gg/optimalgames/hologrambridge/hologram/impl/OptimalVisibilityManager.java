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

package gg.optimalgames.hologrambridge.hologram.impl;

import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The default {@link VisibilityManager} implementation
 */
public class OptimalVisibilityManager implements VisibilityManager {

    private final List<UUID> viewers = new ArrayList<>();

    private final Hologram hologram;
    private final Connector connector;

    private boolean visibleByDefault;

    /**
     * Construct the default {@link VisibilityManager} for a {@link Hologram}
     *
     * @param hologram The {@link Hologram} parent
     */
    public OptimalVisibilityManager(final Hologram hologram,
                                    final Connector connector) {
        this.hologram = hologram;
        this.connector = connector;
        this.visibleByDefault = true;
    }

    @Override
    public Hologram getHologram() {
        return this.hologram;
    }

    @Override
    public boolean isVisibleByDefault() {
        return this.visibleByDefault;
    }

    @Override
    public void setVisibleByDefault(final boolean visibleByDefault) {
        this.visibleByDefault = visibleByDefault;
        this.connector.setVisibleByDefault(this, visibleByDefault);
    }

    @Override
    public void showTo(final Player player) {
        this.viewers.add(player.getUniqueId());
        this.connector.showTo(this, player);
    }

    @Override
    public void hideTo(final Player player) {
        this.viewers.remove(player.getUniqueId());
        this.connector.hideTo(this, player);
    }

    @Override
    public boolean isVisibleTo(final Player player) {
        return this.viewers.contains(player.getUniqueId());
    }

    @Override
    public void resetVisibility(final Player player) {
        if (this.visibleByDefault) {
            this.viewers.add(player.getUniqueId());
            this.connector.showTo(this, player);
            return;
        }

        this.viewers.remove(player.getUniqueId());
        this.connector.hideTo(this, player);
    }

    @Override
    public void resetVisibilityAll() {
        Bukkit.getOnlinePlayers().forEach(this::resetVisibility);
    }

}
