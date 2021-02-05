package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureRadiusConfiguration implements WorldGenFeatureConfiguration {

    public final int a;

    public WorldGenFeatureRadiusConfiguration(int i) {
        this.a = i;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("radius"), dynamicops.createInt(this.a))));
    }

    public static <T> WorldGenFeatureRadiusConfiguration a(Dynamic<T> dynamic) {
        int i = dynamic.get("radius").asInt(0);

        return new WorldGenFeatureRadiusConfiguration(i);
    }
}
