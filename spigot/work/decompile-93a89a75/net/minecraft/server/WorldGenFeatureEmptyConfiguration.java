package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureEmptyConfiguration implements WorldGenFeatureConfiguration {

    public WorldGenFeatureEmptyConfiguration() {}

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }

    public static <T> WorldGenFeatureEmptyConfiguration a(Dynamic<T> dynamic) {
        return WorldGenFeatureConfiguration.e;
    }
}
