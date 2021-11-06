package me.chubbyduck.holobridge.impl;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.line.HologramLine;
import me.chubbyduck.holobridge.HologramBridge;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.ItemLine;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class HolographicDisplaysImpl implements Connector {

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

        if(line instanceof me.chubbyduck.holobridge.lines.impl.ItemLine) {
            hologram.insertItemLine(lineIndex, ((ItemLine) line).getItemStack());
            return;
        }

        if(line instanceof me.chubbyduck.holobridge.lines.impl.TextLine) {
            hologram.insertTextLine(lineIndex, ((TextLine) line).getText());
        }
    }

    @Override
    public void updateLine(Hologram stormHologram, int lineIndex, Line line) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(stormHologram);
        HologramLine hologramLine = hologram.getLine(lineIndex);

        if(hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.ItemLine
                && line instanceof ItemLine) {

            ItemStack itemStack = ((me.chubbyduck.holobridge.lines.impl.ItemLine) line).getItemStack();
            ((com.gmail.filoghost.holographicdisplays.api.line.ItemLine) hologramLine).setItemStack(itemStack);

            return;
        }

        if(hologramLine instanceof com.gmail.filoghost.holographicdisplays.api.line.TextLine
                && line instanceof TextLine) {

            String text = ((TextLine) line).getText();
            ((com.gmail.filoghost.holographicdisplays.api.line.TextLine) hologramLine).setText(text);

        }
    }

    @Override
    public void appendLine(Hologram stormHologram, Line line) {
        com.gmail.filoghost.holographicdisplays.api.Hologram hologram = getHologram(stormHologram);

        if(line instanceof ItemLine) {

            ItemStack itemStack = ((ItemLine) line).getItemStack();
            hologram.appendItemLine(itemStack);

            return;
        }

        if(line instanceof TextLine) {

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

    public com.gmail.filoghost.holographicdisplays.api.Hologram getHologram(Hologram hologram) {
        return (com.gmail.filoghost.holographicdisplays.api.Hologram) hologram.getHologramObject();
    }

}
