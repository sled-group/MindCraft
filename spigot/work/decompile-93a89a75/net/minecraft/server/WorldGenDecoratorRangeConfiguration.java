package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorRangeConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final int b;

    public WorldGenDecoratorRangeConfiguration(int i, int j) {
        this.a = i;
        this.b = j;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("min"), dynamicops.createInt(this.a), dynamicops.createString("max"), dynamicops.createInt(this.b))));
    }

    public static WorldGenDecoratorRangeConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("min").asInt(0);
        int j = dynamic.get("max").asInt(0);

        return new WorldGenDecoratorRangeConfiguration(i, j);
    }
}
