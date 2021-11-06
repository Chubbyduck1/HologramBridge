package me.chubbyduck.holobridge;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import me.chubbyduck.holobridge.interfaces.Connector;
import me.chubbyduck.holobridge.objects.Hologram;
import org.bukkit.Location;

@UtilityClass
public class HologramAPI {

    @Getter @Setter
    private Connector connector;

    public static Hologram createHologram(Location location) {
        return connector.createHologram(location);
    }

}
