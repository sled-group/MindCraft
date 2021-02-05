package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorHeightAverageConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final int b;
    public final int c;

    public WorldGenDecoratorHeightAverageConfiguration(int i, int j, int k) {
        this.a = i;
        this.b = j;
        this.c = k;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a), dynamicops.createString("baseline"), dynamicops.createInt(this.b), dynamicops.createString("spread"), dynamicops.createInt(this.c))));
    }

    public static WorldGenDecoratorHeightAverageConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("count").asInt(0);
        int j = dynamic.get("baseline").asInt(0);
        int k = dynamic.get("spread").asInt(0);

        return new WorldGenDecoratorHeightAverageConfiguration(i, j, k);
    }
}
