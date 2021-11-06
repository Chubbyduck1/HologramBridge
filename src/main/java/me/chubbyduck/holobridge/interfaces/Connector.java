package me.chubbyduck.holobridge.interfaces;

import me.chubbyduck.holobridge.lines.Line;
import me.chubbyduck.holobridge.objects.Hologram;
import org.bukkit.Location;

public interface Connector {

    Hologram createHologram(Location location);

    void setLine(Hologram hologram, int lineIndex, Line line);
    void updateLine(Hologram hologram, int lineIndex, Line line);
    void appendLine(Hologram hologram, Line line);

    void teleport(Hologram hologram, Location location);
    void delete(Hologram hologram);

}
