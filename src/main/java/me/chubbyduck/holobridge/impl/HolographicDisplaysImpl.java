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

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import me.chubbyduck.holobridge.HologramBridge;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.ItemLine;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.objects.VisibilityManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class HolographicDisplaysImpl implements Connector {

    @Override
    public Hologram createHologram(Location location) {

        return new Hologram(
                this,
                HologramsAPI.createHologram(HologramBridge.getInstance().getJavaPlugin(), location),
                location
        );

    }

    @Override
    public void setLine(Hologram stormHologram, int lineIndex, Line line) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(stormHologram);
        hologram.removeLine(lineIndex);

        if (line instanceof me.chubbyduck.holobridge.lines.impl.ItemLine) {
            hologram.insertItemLine(lineIndex, ((ItemLine) line).getItemStack());
            return;
        }

        if (line instanceof me.chubbyduck.holobridge.lines.impl.TextLine) {
            hologram.insertTextLine(lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void updateLine(Hologram stormHologram, int lineIndex, Line line) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(stormHologram);
        HologramLine hologramLine = hologram.getLine(lineIndex);

        if (line instanceof ItemLine
                && hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.ItemLine) {

            ItemStack itemStack = ((me.chubbyduck.holobridge.lines.impl.ItemLine) line).getItemStack();
            ((com.gmail.filoghost.holographicdisplays.api.line.ItemLine) hologramLine).setItemStack(itemStack);

            return;
        }

        if (line instanceof TextLine
                && hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.TextLine) {

            String text = ((TextLine) line).getText();
            ((com.gmail.filoghost.holographicdisplays.api.line.TextLine) hologramLine).setText(text);

        }
    }

    @Override
    public void appendLine(Hologram stormHologram, Line line) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(stormHologram);

        if (line instanceof ItemLine) {

            ItemStack itemStack = ((ItemLine) line).getItemStack();
            hologram.appendItemLine(itemStack);

            return;
        }

        if (line instanceof TextLine) {

            String text = ((TextLine) line).getText();
            hologram.appendTextLine(text);

        }
    }

    @Override
    public void teleport(Hologram hologram, Location location) {
        getHologram(hologram).teleport(location);
    }

    @Override
    public void delete(Hologram hologram) {
        getHologram(hologram).delete();
    }

    @Override
    public void showTo(VisibilityManager visibilityManager, Player player) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(visibilityManager.getHologram());
        com.gmail.filoghost.holographicdisplays.api.VisibilityManager hologramVisibilityManager
                = hologram.getVisibilityManager();

        hologramVisibilityManager.showTo(player);
    }

    @Override
    public void hideTo(VisibilityManager visibilityManager, Player player) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(visibilityManager.getHologram());
        com.gmail.filoghost.holographicdisplays.api.VisibilityManager hologramVisibilityManager
                = hologram.getVisibilityManager();

        hologramVisibilityManager.hideTo(player);
    }

    public com.gmail.filoghost.holographicdisplays.api.Hologram getHologram(Hologram hologram) {
        return (com.gmail.filoghost.holographicdisplays.api.Hologram) hologram.getHologramObject();
    }

}
