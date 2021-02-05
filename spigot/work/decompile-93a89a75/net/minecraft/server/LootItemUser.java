package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import java.util.function.Function;

public interface LootItemUser {

    default Set<LootContextParameter<?>> a() {
        return ImmutableSet.of();
    }

    default void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        lootcontextparameterset.a(lootcollector, this);
    }
}
