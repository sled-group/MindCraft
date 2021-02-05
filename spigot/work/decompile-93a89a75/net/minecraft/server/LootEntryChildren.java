package net.minecraft.server;

import java.util.Objects;
import java.util.function.Consumer;

@FunctionalInterface
interface LootEntryChildren {

    LootEntryChildren a = (loottableinfo, consumer) -> {
        return false;
    };
    LootEntryChildren b = (loottableinfo, consumer) -> {
        return true;
    };

    boolean expand(LootTableInfo loottableinfo, Consumer<LootEntry> consumer);

    default LootEntryChildren a(LootEntryChildren lootentrychildren) {
        Objects.requireNonNull(lootentrychildren);
        return (loottableinfo, consumer) -> {
            return this.expand(loottableinfo, consumer) && lootentrychildren.expand(loottableinfo, consumer);
        };
    }

    default LootEntryChildren b(LootEntryChildren lootentrychildren) {
        Objects.requireNonNull(lootentrychildren);
        return (loottableinfo, consumer) -> {
            return this.expand(loottableinfo, consumer) || lootentrychildren.expand(loottableinfo, consumer);
        };
    }
}
