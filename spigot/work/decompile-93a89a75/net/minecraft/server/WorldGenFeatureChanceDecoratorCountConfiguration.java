package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureChanceDecoratorCountConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final int b;
    public final int c;
    public final int d;

    public WorldGenFeatureChanceDecoratorCountConfiguration(int i, int j, int k, int l) {
        this.a = i;
        this.b = j;
        this.c = k;
        this.d = l;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a), dynamicops.createString("bottom_offset"), dynamicops.createInt(this.b), dynamicops.createString("top_offset"), dynamicops.createInt(this.c), dynamicops.createString("maximum"), dynamicops.createInt(this.d))));
    }

    public static WorldGenFeatureChanceDecoratorCountConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("count").asInt(0);
        int j = dynamic.get("bottom_offset").asInt(0);
        int k = dynamic.get("top_offset").asInt(0);
        int l = dynamic.get("maximum").asInt(0);

        return new WorldGenFeatureChanceDecoratorCountConfiguration(i, j, k, l);
    }
}
