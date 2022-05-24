package me.chubbyduck.holobridge.impl;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.DecentHologramsAPI;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.objects.VisibilityManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.concurrent.ThreadLocalRandom;

public final class DecentImpl implements Connector {

    @Override
    public Hologram createHologram(final Location location) {
        return new Hologram(this,
                DHAPI.createHologram("abyss-" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), location, false),
                location);
    }

    @Override
    public void setLine(final Hologram hologram, final int lineIndex, final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        DHAPI.setHologramLine(this.getHologram(hologram), lineIndex, ((TextLine) line).getText());
    }

    @Override
    public void updateLine(final Hologram hologram, final int lineIndex, final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        DHAPI.setHologramLine(this.getHologram(hologram), lineIndex, ((TextLine) line).getText());
    }

    @Override
    public void appendLine(final Hologram hologram, final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        DHAPI.addHologramLine(this.getHologram(hologram), ((TextLine) line).getText());
    }

    @Override
    public void teleport(final Hologram hologram, final Location location) {
        DHAPI.moveHologram(this.getHologram(hologram), location);
    }

    @Override
    public void delete(final Hologram hologram) {
        DHAPI.removeHologram(this.getHologram(hologram).getName());
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager, final Player player) {
        throw new UnsupportedOperationException("DecentHolograms does not support per-player holograms!");
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager, final Player player) {
        throw new UnsupportedOperationException("DecentHolograms does not support per-player holograms!");
    }

    public eu.decentsoftware.holograms.api.holograms.Hologram getHologram(final Hologram hologram) {
        return (eu.decentsoftware.holograms.api.holograms.Hologram) hologram.getHologramObject();
    }

}