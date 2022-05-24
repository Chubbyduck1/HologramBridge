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
