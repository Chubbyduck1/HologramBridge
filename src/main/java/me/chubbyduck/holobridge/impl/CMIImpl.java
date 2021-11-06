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

import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.ItemLine;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import org.bukkit.Location;

import java.util.UUID;

public class CMIImpl implements Connector {

    @Override
    public Hologram createHologram(Location location) {

        return new Hologram(
                this,
                new CMIHologram(UUID.randomUUID().toString(), location),
                location
        );

    }

    @Override
    public void setLine(Hologram stormHologram, int lineIndex, Line line) {
        CMIHologram hologram = getHologram(stormHologram);

        if(line instanceof ItemLine) {
            return;
        }

        if(line instanceof TextLine) {
            hologram.setLine(lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void updateLine(Hologram stormHologram, int lineIndex, Line line) {
        CMIHologram hologram = getHologram(stormHologram);

        if(line instanceof ItemLine) {
            return;
        }

        if(line instanceof TextLine) {

            String text = ((TextLine) line).getText();
            hologram.setLine(lineIndex, text);

        }
    }

    @Override
    public void appendLine(Hologram stormHologram, Line line) {
        CMIHologram hologram = getHologram(stormHologram);

        if(line instanceof ItemLine) {
            return;
        }

        if(line instanceof TextLine) {

            String text = ((TextLine) line).getText();
            hologram.addLine(text);

        }
    }

    @Override
    public void teleport(Hologram hologram, Location location) {
        getHologram(hologram).setLoc(location);
    }

    @Override
    public void delete(Hologram hologram) {
        getHologram(hologram).remove();
    }

    public CMIHologram getHologram(Hologram hologram) {
        return (CMIHologram) hologram.getHologramObject();
    }

}
