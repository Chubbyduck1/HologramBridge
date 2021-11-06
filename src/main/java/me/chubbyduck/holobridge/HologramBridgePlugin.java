package me.chubbyduck.holobridge;

import me.chubbyduck.holobridge.objects.Hologram;
import me.chubbyduck.holobridge.lines.impl.TextLine;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class HologramBridgePlugin extends JavaPlugin {

    int counter = 0;

    @Override
    public void onEnable() {
        new HologramBridge(this, true);

        for(Player player : Bukkit.getOnlinePlayers()) {

            Hologram hologram = HologramAPI.createHologram(player.getLocation());
            hologram.appendItemLine(new ItemStack(Material.DIAMOND, 1));
            TextLine textLine = hologram.appendTextLine("&cHey!");

            Bukkit.getScheduler().runTaskTimer(this, () -> {
                counter++;
                textLine.setText("&cCounter: " + counter);
            }, 1, 1);

        }
    }
}
