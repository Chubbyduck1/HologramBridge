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

package me.chubbyduck.holobridge.objects;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VisibilityManager {

    @Getter
    private final Hologram hologram;

    private final List<UUID> viewers = new ArrayList<>();

    @Getter
    @Setter
    private boolean visibleByDefault;

    public VisibilityManager(Hologram hologram) {
        this.hologram = hologram;
        this.visibleByDefault = true;
    }

    public void showTo(Player player) {
        viewers.add(player.getUniqueId());
    }

    public void hideTo(Player player) {
        viewers.remove(player.getUniqueId());
    }

    public boolean isVisibleTo(Player player) {
        return viewers.contains(player.getUniqueId());
    }

    public void resetVisibility(Player player) {
        if (visibleByDefault) {
            viewers.add(player.getUniqueId());
            return;
        }

        viewers.remove(player.getUniqueId());
    }

    public void resetVisibilityAll() {
        Bukkit.getOnlinePlayers().forEach(this::resetVisibility);
    }

}
