package gg.optimalgames.hologrambridge.hologram;

import gg.optimalgames.hologrambridge.connector.Connector;
import gg.optimalgames.hologrambridge.lines.Line;
import gg.optimalgames.hologrambridge.lines.types.ItemLine;
import gg.optimalgames.hologrambridge.lines.types.TextLine;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * The interface which handles {@link Hologram} connections
 */
public interface Hologram {

    /**
     * Get the {@link Collection} of {@link Line}s that the {@link Hologram} has displayed
     * @return The {@link Collection} of {@link Hologram} {@link Line}s
     */
    Collection<Line> getLines();

    /**
     * Get a {@link Line} at an index
     * @param index The index to use to fetch the line
     * @return The {@link Line}
     */
    Line getLineAt(final int index);

    /**
     * Get the index of a {@link Line}
     * @param line The {@link Line} to find the index for
     * @return The {@link Line} index
     */
    int getLineIndex(final Line line);

    /**
     * Set a {@link Line} at an index
     * @param index The index to set
     * @param line The {@link Line} to set at that index
     */
    void setLineAt(final int index, final Line line);

    /**
     * Update a {@link Line} at an index
     * @param index The index to update
     * @param line The {@link Line} to change it to
     */
    void updateLine(final int index, final Line line);

    /**
     * Append a {@link Line} to the {@link Hologram}
     * @param line The {@link Line} to append
     */
    void appendLine(final Line line);

    /**
     * Append an {@link ItemLine} to the {@link Hologram} with an {@link ItemStack} display
     * @param itemStack The {@link ItemStack} to display
     * @return The {@link ItemLine} created
     */
    ItemLine appendItemLine(final ItemStack itemStack);

    /**
     * Append a {@link TextLine} to the {@link Hologram} with a {@link String} display
     * @param text The {@link String} to display
     * @return The {@link TextLine} created
     */
    TextLine appendTextLine(final String text);

    /**
     * Get the {@link VisibilityManager} for the {@link Hologram}
     * @return The {@link VisibilityManager}
     */
    VisibilityManager getVisibilityManager();

    /**
     * Get the {@link Hologram}s current {@link Location}
     * @return The {@link Location}
     */
    Location getLocation();

    /**
     * Teleport the {@link Hologram} to a different location
     * @param location The new {@link Hologram} {@link Location}
     */
    void teleport(final Location location);

    /**
     * Delete the {@link Hologram}, and all attached entities
     */
    void delete();

    /**
     * Get the size of all the attached {@link Line}s
     * @return The size of the {@link Hologram} {@link Line}s
     */
    int size();

    /**
     * Get the {@link Hologram} as a {@link Object} from the {@link Connector} source
     * @return The {@link Object}
     */
    Object getHologramAsObject();

}
