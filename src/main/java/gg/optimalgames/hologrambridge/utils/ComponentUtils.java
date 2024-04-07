package gg.optimalgames.hologrambridge.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class ComponentUtils {

    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
            .hexColors()
            .character('&')
            .build();

    @NotNull
    public static String toLegacy(final @NotNull Component component) {
        return LEGACY_SERIALIZER.serialize(component);
    }

}
