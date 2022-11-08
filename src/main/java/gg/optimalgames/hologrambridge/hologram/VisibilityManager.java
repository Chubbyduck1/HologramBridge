/*
 * MIT License
 *
 * Copyright (c) 2021-2022 Chubbyduck1
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package gg.optimalgames.hologrambridge.hologram;

import org.bukkit.entity.Player;

/**
 * The {@link VisibilityManager} which manages {@link Hologram} visibility
 */
public interface VisibilityManager {

    /**
     * Get the linked {@link Hologram} that this {@link VisibilityManager} represents
     * @return The {@link Hologram}
     */
    Hologram getHologram();

    /**
     * Check if the {@link Hologram} is visible to players by default
     * @return The {@link Boolean} value for if its visible by default.
     */
    boolean isVisibleByDefault();

    /**
     * Set the {@link Hologram} visible by default {@link Boolean} value
     * @param visibleByDefault The new visible by default {@link Boolean}
     */
    void setVisibleByDefault(final boolean visibleByDefault);

    /**
     * Show the {@link Hologram} to a {@link Player}
     * @param player The {@link Player} to display the {@link Hologram} to
     */
    void showTo(final Player player);

    /**
     * Hide the {@link Hologram} from a {@link Player}
     * @param player The {@link Player} to hide the {@link Hologram} from
     */
    void hideTo(final Player player);

    /**
     * Check if the {@link Hologram} is visible to a {@link Player}
     * @param player The {@link Player} to check the visibility status of
     * @return The {@link Boolean} visibility status
     */
    boolean isVisibleTo(final Player player);

    /**
     * Reset the visibility of the {@link Hologram} for a {@link Player}
     * @param player The {@link Player} to reset the visibility for
     */
    void resetVisibility(final Player player);

    /**
     * Reset the visibility for all online {@link Player}s
     */
    void resetVisibilityAll();

}
