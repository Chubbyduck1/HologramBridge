package me.chubbyduck.holobridge.lines.impl;

import lombok.Getter;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.lines.Line;
import org.bukkit.ChatColor;

public class TextLine extends Line {

    private final Hologram hologram;

    @Getter
    private String text;

    public TextLine(Hologram hologram, String text) {
        this.hologram = hologram;
        this.text = ChatColor.translateAlternateColorCodes('&', text);
    }

    public void setText(String text) {
        this.text = ChatColor.translateAlternateColorCodes('&', text);
        update(hologram);
    }

}
