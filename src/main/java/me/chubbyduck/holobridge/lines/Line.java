package me.chubbyduck.holobridge.lines;

import me.chubbyduck.holobridge.objects.Hologram;

public abstract class Line {

    public void update(Hologram hologram) {
        hologram.updateLine(
                hologram.getLineIndex(this),
                this
        );
    }

}
