package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureDecoratorEmptyConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public WorldGenFeatureDecoratorEmptyConfiguration() {}

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }

    public static WorldGenFeatureDecoratorEmptyConfiguration a(Dynamic<?> dynamic) {
        return new WorldGenFeatureDecoratorEmptyConfiguration();
    }
}
