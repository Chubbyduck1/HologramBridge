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
package gg.optimalgames.hologrambridge.connector.impl.holographic;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import gg.optimalgames.hologrambridge.HologramAPI;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.ItemLine;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public final class Holographic2 implements Connector {

    @Override
    public Hologram createHologram(final Location location) {
        return new OptimalHologram(
                this,
                HologramsAPI.createHologram(HologramAPI.getJavaPlugin(), location),
                location
        );
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        this.modifyHologram(hologram, holo -> {
            holo.removeLine(lineIndex);

            if (line instanceof ItemLine) {
                holo.insertItemLine(lineIndex, ((ItemLine) line).getItemStack());
                return;
            }

            if (line instanceof TextLine) {
                holo.insertTextLine(lineIndex, ((TextLine) line).getText());
            }
        });
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        this.modifyHologram(hologram, holo -> {
            final HologramLine hologramLine = holo.getLine(lineIndex);

            if (line instanceof ItemLine
                && hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.ItemLine) {
                final ItemStack itemStack = ((ItemLine) line).getItemStack();
                ((com.gmail.filoghost.holographicdisplays.api.line.ItemLine) hologramLine).setItemStack(itemStack);
                return;
            }

            if (line instanceof TextLine
                && hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.TextLine) {
                final String text = ((TextLine) line).getText();
                ((com.gmail.filoghost.holographicdisplays.api.line.TextLine) hologramLine).setText(text);
            }
        });
    }

    @Override
    public void appendLine(final Hologram hologram, final Line line) {
        this.modifyHologram(hologram, holo -> {
            if (line instanceof ItemLine) {
                final ItemStack itemStack = ((ItemLine) line).getItemStack();
                holo.appendItemLine(itemStack);

                return;
            }

            if (line instanceof TextLine) {
                final String text = ((TextLine) line).getText();
                holo.appendTextLine(text);
            }
        });
    }

    @Override
    public void clearLines(final Hologram hologram) {
        this.modifyHologram(hologram, com.gmail.filoghost.holographicdisplays.api.Hologram::clearLines);
    }

    @Override
    public void teleport(final Hologram hologram, final Location location) {
        this.modifyHologram(hologram, holo -> holo.teleport(location));
    }

    @Override
    public void delete(final Hologram hologram) {
        this.modifyHologram(hologram, com.gmail.filoghost.holographicdisplays.api.Hologram::delete);
    }

    @Override
    public void setVisibleByDefault(final VisibilityManager visibilityManager, final boolean visibleByDefault) {
        this.modifyHologram(visibilityManager.getHologram(), holo -> holo.getVisibilityManager().setVisibleByDefault(visibleByDefault));
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager, final Player player) {
        this.modifyHologram(visibilityManager.getHologram(), holo -> holo.getVisibilityManager().showTo(player));
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager, final Player player) {
        this.modifyHologram(visibilityManager.getHologram(), holo -> holo.getVisibilityManager().hideTo(player));
    }

    private void modifyHologram(final Hologram hologram,
                                final Consumer<com.gmail.filoghost.holographicdisplays.api.Hologram> consumer) {
        final Object hologramObject = hologram.getHologramAsObject();

        if (!(hologramObject instanceof com.gmail.filoghost.holographicdisplays.api.Hologram)) {
            return;
        }

        consumer.accept((com.gmail.filoghost.holographicdisplays.api.Hologram) hologramObject);
    }
}
