package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureKelpConfiguration implements WorldGenFeatureConfiguration {

    public final int a;

    public WorldGenFeatureKelpConfiguration(int i) {
        this.a = i;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a))));
    }

    public static <T> WorldGenFeatureKelpConfiguration a(Dynamic<T> dynamic) {
        int i = dynamic.get("count").asInt(0);

        return new WorldGenFeatureKelpConfiguration(i);
    }
}
