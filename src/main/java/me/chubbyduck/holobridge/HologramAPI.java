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

    /**
     * Create a hologram at a location
     * @param location The {@link Location} for the hologram
     * @return The created Hologram
     */
    public static Hologram createHologram(Location location) {
        return connector.createHologram(location);
    }

    /**
     * Check if the HologramAPI has a connector
     * @return The {@link Boolean} value of if a connector is found
     */
    public static boolean hasConnector() {
        return connector != null;
    }

}
