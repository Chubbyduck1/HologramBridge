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

    @Getter
    private final VisibilityManager visibilityManager;

    @Getter @Setter
    private Location location;

    private final List<Line> lines = new ArrayList<>();

    /**
     * Create a new hologram
     * @param connector The connector to use
     * @param hologramObject The hologram object for the connector
     * @param location The starting location
     */
    public Hologram(Connector connector, Object hologramObject, Location location) {
        this.connector = connector;
        this.hologramObject = hologramObject;
        this.location = location;
        this.visibilityManager = new VisibilityManager(this);
    }

    /**
     * Get the amount of lines as an integer
     * @return The {@link Integer} amount of lines
     */
    public int getLineSize() {
        return lines.size();
    }

    /**
     * Get all the lines
     * @return The {@link List} of {@link Line}s
     */
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Get the line at a specific index
     * @param index The index to get the line for
     * @return The {@link Line}
     */
    public Line getLineAt(int index) {
        return lines.get(index);
    }

    /**
     * Get the index of a line
     * @param line The {@link Line} to get the index of
     * @return The {@link Integer} index
     */
    public int getLineIndex(Line line) {
        return this.lines.indexOf(line);
    }

    /**
     * Set a line at a specific index
     * @param index The index to set
     * @param line The line to set
     */
    public void setLineAt(int index, Line line) {
        this.lines.set(index, line);
        this.connector.setLine(this, index, line);
    }

    /**
     * Update a line at a specific index
     * @param index The index to update
     * @param line The line to update
     */
    public void updateLine(int index, Line line) {
        this.connector.updateLine(this, index, line);
    }

    /**
     * Append a line to the hologram
     * @param line The line to append
     */
    public void appendLine(Line line) {
        this.lines.add(line);
        this.connector.appendLine(this, line);
    }

    /**
     * Append an ItemStack line
     * @param itemStack The ItemStack to append
     * @return The {@link ItemLine}
     */
    public ItemLine appendItemLine(ItemStack itemStack) {
        ItemLine itemLine = new ItemLine(this, itemStack);
        this.appendLine(itemLine);

        return itemLine;
    }

    /**
     * Append a Text line
     * @param text The Text to append
     * @return The {@link TextLine}
     */
    public TextLine appendTextLine(String text) {
        TextLine textLine = new TextLine(this, text);
        this.appendLine(textLine);

        return textLine;
    }

    /**
     * Teleport a hologram to a location
     * @param location The location to teleport to
     */
    public void teleport(Location location) {
        this.location = location;

        this.connector.teleport(this, location);
    }

    /**
     * Delete the hologram
     */
    public void delete() {
        this.connector.delete(this);
    }

}
