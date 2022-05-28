package gg.optimalgames.hologrambridge.connector.impl;

import eu.decentsoftware.holograms.api.DHAPI;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public final class DecentImpl implements Connector {

    @Override
    public Hologram createHologram(final Location location) {
        return new OptimalHologram(this,
                DHAPI.createHologram("abyss-" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), location, false),
                location);
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.setHologramLine(decentHologram, lineIndex, ((TextLine) line).getText());
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.setHologramLine(decentHologram, lineIndex, ((TextLine) line).getText());
    }

    @Override
    public void appendLine(final Hologram hologram, final Line line) {
        if (!(line instanceof TextLine)) {
            return;
        }

        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.addHologramLine(decentHologram, ((TextLine) line).getText());
    }

    @Override
    public void teleport(final Hologram hologram, final Location location) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.moveHologram(decentHologram, location);
    }

    @Override
    public void delete(final Hologram hologram) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(hologram);

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        DHAPI.removeHologram(decentHologram.getName());
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager, final Player player) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(visibilityManager.getHologram());

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        decentHologram.show(player, 0);
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager, final Player player) {
        final Optional<eu.decentsoftware.holograms.api.holograms.Hologram> hologramOptimal = this.getHologram(visibilityManager.getHologram());

        if(!hologramOptimal.isPresent()) {
            return;
        }

        final eu.decentsoftware.holograms.api.holograms.Hologram decentHologram = hologramOptimal.get();
        decentHologram.hide(player);
    }

    private Optional<eu.decentsoftware.holograms.api.holograms.Hologram> getHologram(final Hologram hologram) {
        final Object hologramObject = hologram.getHologramAsObject();

        if(hologramObject instanceof eu.decentsoftware.holograms.api.holograms.Hologram) {
            return Optional.of((eu.decentsoftware.holograms.api.holograms.Hologram) hologramObject);
        }

        return Optional.empty();
    }

}