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

package gg.optimalgames.hologrambridge.lines.impl;

import gg.optimalgames.hologrambridge.lines.types.TextLine;
import gg.optimalgames.hologrambridge.hologram.impl.OptimalHologram;
import org.bukkit.ChatColor;

public final class OptimalTextLine implements TextLine {

    private final OptimalHologram hologram;

    private String text;

    /**
     * Create a new Item Line
     *
     * @param hologram The parent {@link OptimalHologram}
     * @param text     The default {@link String} text
     */
    public OptimalTextLine(OptimalHologram hologram, String text) {
        this.hologram = hologram;
        this.text = ChatColor.translateAlternateColorCodes('&', text);
    }

    public String getText() {
        return this.text;
    }

    @Override
    public void setText(String text) {
        this.text = ChatColor.translateAlternateColorCodes('&', text);
        this.updateHologram(hologram);
    }

}
