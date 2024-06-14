package gg.optimalgames.hologrambridge.connector.impl;

import de.oliver.fancyholograms.api.FancyHologramsPlugin;
import de.oliver.fancyholograms.api.HologramManager;
import de.oliver.fancyholograms.api.data.TextHologramData;
import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Display;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class FancyHologramsImpl implements Connector {

    @Override
    public Hologram createHologram(Location location) {
        TextHologramData textData = new TextHologramData("holobridge-" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE), location);
        textData.setBillboard(Display.Billboard.CENTER);
        textData.setBackground(de.oliver.fancyholograms.api.hologram.Hologram.TRANSPARENT);
        textData.setTextUpdateInterval(10);
        textData.setPersistent(false);

        textData.removeLine(0);

        de.oliver.fancyholograms.api.hologram.Hologram hologram = getHologramManager().create(textData);

        hologram.createHologram();
        hologram.showHologram(Bukkit.getOnlinePlayers());

        getHologramManager().addHologram(hologram);
        return new OptimalHologram(this, hologram, location);
    }

    @Override
    public void setLine(Hologram hologram, int lineIndex, Line line) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();

        if (line instanceof TextLine) {
            TextHologramData data = (TextHologramData) fancyHologram.getData();
            List<String> oldLines = data.getText();
            List<String> newLines = new ArrayList<>();

            for (int i = 0; i < data.getText().size(); i++) {
                if (i == lineIndex) newLines.add(((TextLine) line).getText());
                else newLines.add(oldLines.get(i));
            }

            data.setText(newLines);
        } else throw new IllegalArgumentException("only TextLine is supported currently");
    }

    @Override
    public void updateLine(Hologram hologram, int lineIndex, Line line) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();

        if (line instanceof TextLine) {
            TextHologramData data = (TextHologramData) fancyHologram.getData();
            List<String> oldLines = data.getText();
            List<String> newLines = new ArrayList<>();

            for (int i = 0; i < data.getText().size(); i++) {
                if (i == lineIndex) newLines.add(((TextLine) line).getText());
                else newLines.add(oldLines.get(i));
            }

            data.setText(newLines);
        } else throw new IllegalArgumentException("only TextLine is supported currently");
    }

    @Override
    public void appendLine(Hologram hologram, Line line) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();

        if (line instanceof TextLine) {
            TextHologramData data = (TextHologramData) fancyHologram.getData();
            data.addLine(((TextLine) line).getText());
        } else throw new IllegalArgumentException("only TextLine is supported currently");
    }

    @Override
    public void clearLines(Hologram hologram) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();
        TextHologramData data = (TextHologramData) fancyHologram.getData();
        data.setText(new ArrayList<>());
    }

    @Override
    public void teleport(Hologram hologram, Location location) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();
        fancyHologram.getDisplayEntity().teleport(location);
    }

    @Override
    public void delete(Hologram hologram) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(hologram);

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();
        hologram.delete();
    }

    @Override
    public void setVisibleByDefault(VisibilityManager visibilityManager, boolean visibleByDefault) {
        throw new IllegalArgumentException("currently unsupported for FancyHolograms connector");
    }

    @Override
    public void showTo(VisibilityManager visibilityManager, Player player) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(visibilityManager.getHologram());

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();
        fancyHologram.showHologram(player);
    }

    @Override
    public void hideTo(VisibilityManager visibilityManager, Player player) {
        final Optional<de.oliver.fancyholograms.api.hologram.Hologram> hologramOptional = getHologram(visibilityManager.getHologram());

        if (!hologramOptional.isPresent()) {
            return;
        }

        final de.oliver.fancyholograms.api.hologram.Hologram fancyHologram = hologramOptional.get();
        fancyHologram.hideHologram(player);
    }

    @Override
    public double getHeight(Hologram hologram) {
        throw new IllegalArgumentException("currently unsupported for FancyHolograms connector");
    }

    private Optional<de.oliver.fancyholograms.api.hologram.Hologram> getHologram(final Hologram hologram) {
        final Object hologramObject = hologram.getHologramAsObject();

        if (hologramObject instanceof de.oliver.fancyholograms.api.hologram.Hologram) {
            return Optional.of((de.oliver.fancyholograms.api.hologram.Hologram) hologramObject);
        }

        return Optional.empty();
    }

    private HologramManager getHologramManager() {
        return FancyHologramsPlugin.get().getHologramManager();
    }
}
