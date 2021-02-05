package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorFrequencyExtraChanceConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final float b;
    public final int c;

    public WorldGenDecoratorFrequencyExtraChanceConfiguration(int i, float f, int j) {
        this.a = i;
        this.b = f;
        this.c = j;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a), dynamicops.createString("extra_chance"), dynamicops.createFloat(this.b), dynamicops.createString("extra_count"), dynamicops.createInt(this.c))));
    }

    public static WorldGenDecoratorFrequencyExtraChanceConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("count").asInt(0);
        float f = dynamic.get("extra_chance").asFloat(0.0F);
        int j = dynamic.get("extra_count").asInt(0);

        return new WorldGenDecoratorFrequencyExtraChanceConfiguration(i, f, j);
    }
}
