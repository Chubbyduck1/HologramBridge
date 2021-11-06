package me.chubbyduck.holobridge.lines.impl;

import lombok.Getter;
import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.lines.Line;
import org.bukkit.inventory.ItemStack;

public class ItemLine extends Line {

    private final Hologram hologram;

    @Getter
    private ItemStack itemStack;

    public ItemLine(Hologram hologram, ItemStack itemStack) {
        this.hologram = hologram;
        this.itemStack = itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;

        update(hologram);
    }

}
