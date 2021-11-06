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
