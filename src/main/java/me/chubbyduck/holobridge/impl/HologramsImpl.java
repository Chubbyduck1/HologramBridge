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

package me.chubbyduck.holobridge.impl;

import com.sainttx.holograms.api.line.HologramLine;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.ItemLine;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.objects.VisibilityManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class HologramsImpl implements Connector {

    @Override
    public Hologram createHologram(Location location) {

        return new Hologram(
                this,
                new com.sainttx.holograms.api.Hologram(UUID.randomUUID().toString(), location, false),
                location
        );

    }

    @Override
    public void setLine(Hologram stormHologram, int lineIndex, Line line) {
        com.sainttx.holograms.api.Hologram hologram = getHologram(stormHologram);

        hologram.removeLine(
                hologram.getLine(lineIndex)
        );

        if (line instanceof ItemLine) {

            hologram.addLine(
                    new com.sainttx.holograms.api.line.ItemLine(hologram, ((ItemLine) line).getItemStack()),
                    lineIndex
            );

            return;
        }

        if (line instanceof TextLine) {

            hologram.addLine(
                    new com.sainttx.holograms.api.line.TextLine(hologram, ((TextLine) line).getText()),
                    lineIndex
            );

        }
    }

    @Override
    public void updateLine(Hologram stormHologram, int lineIndex, Line line) {
        com.sainttx.holograms.api.Hologram hologram = getHologram(stormHologram);
        HologramLine hologramLine = hologram.getLine(lineIndex);

        if (line instanceof ItemLine
                && hologramLine instanceof com.sainttx.holograms.api.line.ItemLine) {

            ItemStack itemStack = ((ItemLine) line).getItemStack();
            ((com.sainttx.holograms.api.line.ItemLine) hologramLine).setItem(itemStack);

            return;

        }

        if (line instanceof TextLine
                && hologramLine instanceof com.sainttx.holograms.api.line.TextLine) {

            String text = ((TextLine) line).getText();
            ((com.sainttx.holograms.api.line.TextLine) hologramLine).setText(text);

        }
    }

    @Override
    public void appendLine(Hologram stormHologram, Line line) {
        com.sainttx.holograms.api.Hologram hologram = getHologram(stormHologram);

        if (line instanceof ItemLine) {

            hologram.addLine(
                    new com.sainttx.holograms.api.line.ItemLine(hologram, ((ItemLine) line).getItemStack())
            );

            return;
        }

        if (line instanceof TextLine) {

            hologram.addLine(
                    new com.sainttx.holograms.api.line.TextLine(hologram, ((TextLine) line).getText())
            );

        }
    }

    @Override
    public void teleport(Hologram hologram, Location location) {
        getHologram(hologram).teleport(location);
    }

    @Override
    public void delete(Hologram hologram) {
        getHologram(hologram).despawn();
    }

    @Override
    public void showTo(VisibilityManager visibilityManager, Player player) {
        // As far as I know, this plugin doesn't have this
    }

    @Override
    public void hideTo(VisibilityManager visibilityManager, Player player) {
        // As far as I know, this plugin doesn't have this
    }

    public com.sainttx.holograms.api.Hologram getHologram(Hologram hologram) {
        return (com.sainttx.holograms.api.Hologram) hologram.getHologramObject();
    }

}
