package gg.optimalgames.hologrambridge.connector.impl.holographic;

import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.hologram.Hologram;
import gg.optimalgames.hologrambridge.hologram.VisibilityManager;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.ItemLine;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class Holographic3 implements Connector {

    private final HolographicDisplaysAPI holographicAPI;

    public Holographic3(final JavaPlugin javaPlugin) {
        this.holographicAPI = HolographicDisplaysAPI.get(javaPlugin);
    }

    @Override
    public Hologram createHologram(final Location location) {
        return new OptimalHologram(
                this,
                this.holographicAPI.createHologram(location),
                location
        );
    }

    @Override
    public void setLine(final Hologram hologram,
                        final int lineIndex,
                        final Line line) {
        this.modifyHologram(hologram, (
                holo -> {
                    final HologramLines hologramLines = holo.getLines();
                    hologramLines.remove(lineIndex);

                    if (line instanceof ItemLine) {
                        hologramLines.insertItem(lineIndex, ((ItemLine) line).getItemStack());
                        return;
                    }

                    if (line instanceof TextLine) {
                        hologramLines.insertText(lineIndex, ((TextLine) line).getText());
                    }
                }
        ));
    }

    @Override
    public void updateLine(final Hologram hologram,
                           final int lineIndex,
                           final Line line) {
        this.modifyHologram(hologram, (
                holo -> {
                    final HologramLines hologramLines = holo.getLines();
                    final HologramLine hologramLine = hologramLines.get(lineIndex);

                    if (line instanceof ItemLine
                        &&
                        hologramLine instanceof me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine) {
                        final ItemStack itemStack = ((ItemLine) line).getItemStack();
                        ((me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine) hologramLine).setItemStack(itemStack);
                        return;
                    }

                    if (line instanceof TextLine
                        &&
                        hologramLine instanceof me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine) {
                        final String text = ((TextLine) line).getText();
                        ((me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine) hologramLine).setText(text);
                    }
                }
        ));
    }

    @Override
    public void appendLine(final Hologram hologram,
                           final Line line) {
        this.modifyHologram(hologram, (
                holo -> {
                    final HologramLines hologramLines = holo.getLines();

                    if (line instanceof ItemLine) {
                        hologramLines.appendItem(((ItemLine) line).getItemStack());
                        return;
                    }

                    if (line instanceof TextLine) {
                        hologramLines.appendText(((TextLine) line).getText());
                    }
                }
        ));
    }

    @Override
    public void clearLines(final Hologram hologram) {
        this.modifyHologram(hologram, (
                holo -> {
                    final HologramLines lines = holo.getLines();
                    lines.clear();
                }
        ));
    }

    @Override
    public void teleport(final Hologram hologram,
                         final Location location) {
        this.modifyHologram(hologram, (
                holo -> holo.setPosition(location)
        ));
    }

    @Override
    public void delete(final Hologram hologram) {
        this.modifyHologram(hologram, (
                me.filoghost.holographicdisplays.api.hologram.Hologram::delete
        ));
    }

    @Override
    public void setVisibleByDefault(final VisibilityManager visibilityManager,
                                    final boolean visibleByDefault) {
        this.modifyHologram(visibilityManager.getHologram(), (
                hologram -> {
                    final VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();
                    visibilitySettings.setGlobalVisibility(visibleByDefault ? VisibilitySettings.Visibility.VISIBLE : VisibilitySettings.Visibility.HIDDEN);
                }
        ));
    }

    @Override
    public void showTo(final VisibilityManager visibilityManager,
                       final Player player) {
        this.modifyHologram(visibilityManager.getHologram(), (
                hologram -> {
                    final VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();
                    visibilitySettings.setIndividualVisibility(player, VisibilitySettings.Visibility.VISIBLE);
                }
        ));
    }

    @Override
    public void hideTo(final VisibilityManager visibilityManager,
                       final Player player) {
        this.modifyHologram(visibilityManager.getHologram(), (
                hologram -> {
                    final VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();
                    visibilitySettings.setIndividualVisibility(player, VisibilitySettings.Visibility.HIDDEN);
                }
        ));
    }

    private void modifyHologram(final Hologram hologram,
                                final Consumer<me.filoghost.holographicdisplays.api.hologram.Hologram> consumer) {
        final Object hologramObject = hologram.getHologramAsObject();

        if (!(hologramObject instanceof me.filoghost.holographicdisplays.api.hologram.Hologram)) {
            return;
        }

        consumer.accept((me.filoghost.holographicdisplays.api.hologram.Hologram) hologramObject);
    }
}
