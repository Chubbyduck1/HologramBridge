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

import com.Zrips.CMI.Modules.Holograms.CMIHologram;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.ItemLine;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import net.Zrips.CMILib.Container.CMILocation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public final class CMIImpl implements Connector {

    @Override
    public Hologram createHologram(final Location location) {
        return new OptimalHologram(
                this,
                new CMIHologram(UUID.randomUUID().toString(), new CMILocation(location)),
                location
        );
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(hologram);

        if(!hologramOptional.isPresent()) {
            return;
        }

        final CMIHologram cmiHologram = hologramOptional.get();

        if (line instanceof ItemLine) {
            return;
        }

        if (line instanceof TextLine) {
            cmiHologram.setLine(lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(hologram);

        if(!hologramOptional.isPresent()) {
            return;
        }

        final CMIHologram cmiHologram = hologramOptional.get();

        if (line instanceof ItemLine) {
            return;
        }

        if (line instanceof TextLine) {
            final String text = ((TextLine) line).getText();
            cmiHologram.setLine(lineIndex, text);
        }
    }

    @Override
    public void appendLine(final Hologram hologram, final Line line) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(hologram);

        if(!hologramOptional.isPresent()) {
            return;
        }

        final CMIHologram cmiHologram = hologramOptional.get();

        if (line instanceof ItemLine) {
            return;
        }

        if (line instanceof TextLine) {
            final String text = ((TextLine) line).getText();
            cmiHologram.addLine(text);
        }
    }

    @Override
    public void teleport(final Hologram hologram, final Location location) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(hologram);

        if(!hologramOptional.isPresent()) {
            return;
        }

        final CMIHologram cmiHologram = hologramOptional.get();
        cmiHologram.setLoc(location);
    }

    @Override
    public void delete(final Hologram hologram) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(hologram);
        hologramOptional.ifPresent(CMIHologram::remove);
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager, final Player player) {
        throw new UnsupportedOperationException("CMI does not support per-player holograms!");
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager, final Player player) {
        final Optional<CMIHologram> hologramOptional = this.getHologram(visibilityManager.getHologram());

        if(!hologramOptional.isPresent()) {
            return;
        }

        final CMIHologram cmiHologram = hologramOptional.get();
        cmiHologram.hide(player.getUniqueId());
    }

    private Optional<CMIHologram> getHologram(final Hologram hologram) {
        final Object hologramObject = hologram.getHologramAsObject();

        if(hologramObject instanceof CMIHologram) {
            return Optional.of((CMIHologram) hologramObject);
        }

        return Optional.empty();
    }

}
