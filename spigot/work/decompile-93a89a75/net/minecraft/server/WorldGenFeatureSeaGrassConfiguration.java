package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureSeaGrassConfiguration implements WorldGenFeatureConfiguration {

    public final int a;
    public final double b;

    public WorldGenFeatureSeaGrassConfiguration(int i, double d0) {
        this.a = i;
        this.b = d0;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a), dynamicops.createString("tall_seagrass_probability"), dynamicops.createDouble(this.b))));
    }

    public static <T> WorldGenFeatureSeaGrassConfiguration a(Dynamic<T> dynamic) {
        int i = dynamic.get("count").asInt(0);
        double d0 = dynamic.get("tall_seagrass_probability").asDouble(0.0D);

        return new WorldGenFeatureSeaGrassConfiguration(i, d0);
    }
}
