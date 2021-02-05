package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureChanceDecoratorRangeConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final float a;
    public final int b;
    public final int c;
    public final int d;

    public WorldGenFeatureChanceDecoratorRangeConfiguration(float f, int i, int j, int k) {
        this.a = f;
        this.b = i;
        this.c = j;
        this.d = k;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("chance"), dynamicops.createFloat(this.a), dynamicops.createString("bottom_offset"), dynamicops.createInt(this.b), dynamicops.createString("top_offset"), dynamicops.createInt(this.c), dynamicops.createString("top"), dynamicops.createInt(this.d))));
    }

    public static WorldGenFeatureChanceDecoratorRangeConfiguration a(Dynamic<?> dynamic) {
        float f = dynamic.get("chance").asFloat(0.0F);
        int i = dynamic.get("bottom_offset").asInt(0);
        int j = dynamic.get("top_offset").asInt(0);
        int k = dynamic.get("top").asInt(0);

        return new WorldGenFeatureChanceDecoratorRangeConfiguration(f, i, j, k);
    }
}
