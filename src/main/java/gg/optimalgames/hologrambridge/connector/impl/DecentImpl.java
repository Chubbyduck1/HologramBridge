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

import eu.decentsoftware.holograms.api.DHAPI;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.ItemLine;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public final class DecentImpl implements Connector {

    @Override
    public Hologram createHologram(final Location location) {
        return new OptimalHologram(this,
                DHAPI.createHologram(
                        "holobridge-" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), location, false),
                location);
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();

        if (line instanceof ItemLine) {
            DHAPI.setHologramLine(decentHologram, lineIndex, ((ItemLine) line).getItemStack());
            return;
        }

        if (line instanceof TextLine) {
            DHAPI.setHologramLine(decentHologram, lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();

        if (line instanceof ItemLine) {
            DHAPI.setHologramLine(decentHologram, lineIndex, ((ItemLine) line).getItemStack());
            return;
        }

        if (line instanceof TextLine) {
            DHAPI.setHologramLine(decentHologram, lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void appendLine(final Hologram hologram, final Line line) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();

        if (line instanceof ItemLine) {
            DHAPI.addHologramLine(decentHologram, ((ItemLine) line).getItemStack());
            return;
        }

        if (line instanceof TextLine) {
            DHAPI.addHologramLine(decentHologram, ((TextLine) line).getText());
        }
    }

    @Override
    public void clearLines(final Hologram hologram) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.setHologramLines(decentHologram, Collections.emptyList());
    }

    @Override
    public void teleport(final Hologram hologram, final Location location) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.moveHologram(decentHologram, location);
    }

    @Override
    public void delete(final Hologram hologram) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.removeHologram(decentHologram.getName());
    }

    @Override
    public void setVisibleByDefault(final VisibilityManager visibilityManager, final boolean visibleByDefault) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(visibilityManager.getHologram());

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        decentHologram.setDefaultVisibleState(visibleByDefault);
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager, final Player player) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(visibilityManager.getHologram());

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        decentHologram.setShowPlayer(player);
        decentHologram.show(player, 0);
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager, final Player player) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(visibilityManager.getHologram());

        if (!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        decentHologram.setHidePlayer(player);
        decentHologram.hide(player);
    }

    @Override
    public double getHeight(final Hologram hologram) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if (!hologramOptimal.isPresent()) {
            return 0;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        return decentHologram.getPage(0).getHeight();
    }

    private Optional<eu.decentsoftware.holograms.api.holograms.Hologram> getHologram(final Hologram hologram) {
        final Object hologramObject = hologram.getHologramAsObject();

        if (hologramObject instanceof eu.decentsoftware.holograms.api.holograms.Hologram) {
            return Optional.of((eu.decentsoftware.holograms.api.holograms.Hologram) hologramObject);
        }

        return Optional.empty();
    }

}