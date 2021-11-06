package me.chubbyduck.holobridge.objects;

import lombok.Getter;
import lombok.Setter;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.lines.impl.ItemLine;
import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    @Getter
    private final Connector connector;

    @Getter
    private final Object hologramObject;

    @Getter @Setter
    private Location location;

    private final List<Line> lines = new ArrayList<>();

    public Hologram(Connector connector, Object hologramObject, Location location) {
        this.connector = connector;
        this.hologramObject = hologramObject;
        this.location = location;
    }

    public int getLineSize() {
        return lines.size();
    }

    public List<Line> getLines() {
        return lines;
    }

    public Line getLineAt(int index) {
        return lines.get(index);
    }

    public int getLineIndex(Line line) {
        return this.lines.indexOf(line);
    }

    public void setLineAt(int index, Line line) {
        this.lines.set(index, line);
        this.connector.setLine(this, index, line);
    }

    public void updateLine(int index, Line line) {
        this.connector.updateLine(this, index, line);
    }

    public void appendLine(Line line) {
        this.lines.add(line);
        this.connector.appendLine(this, line);
    }

    public ItemLine appendItemLine(ItemStack itemStack) {
        ItemLine itemLine = new ItemLine(this, itemStack);
        this.appendLine(itemLine);

        return itemLine;
    }

    public TextLine appendTextLine(String text) {
        TextLine textLine = new TextLine(this, text);
        this.appendLine(textLine);

        return textLine;
    }

    public void teleport(Location location) {
        this.location = location;

        this.connector.teleport(this, location);
    }

    public void delete() {
        this.connector.delete(this);
    }

}
