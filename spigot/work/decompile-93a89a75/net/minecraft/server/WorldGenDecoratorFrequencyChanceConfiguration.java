package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenDecoratorFrequencyChanceConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final int a;
    public final float b;

    public WorldGenDecoratorFrequencyChanceConfiguration(int i, float f) {
        this.a = i;
        this.b = f;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("count"), dynamicops.createInt(this.a), dynamicops.createString("chance"), dynamicops.createFloat(this.b))));
    }

    public static WorldGenDecoratorFrequencyChanceConfiguration a(Dynamic<?> dynamic) {
        int i = dynamic.get("count").asInt(0);
        float f = dynamic.get("chance").asFloat(0.0F);

        return new WorldGenDecoratorFrequencyChanceConfiguration(i, f);
    }
}
