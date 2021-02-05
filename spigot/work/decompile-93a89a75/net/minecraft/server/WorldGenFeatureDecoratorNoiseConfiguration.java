package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureDecoratorNoiseConfiguration implements WorldGenFeatureDecoratorConfiguration {

    public final double a;
    public final int b;
    public final int c;

    public WorldGenFeatureDecoratorNoiseConfiguration(double d0, int i, int j) {
        this.a = d0;
        this.b = i;
        this.c = j;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("noise_level"), dynamicops.createDouble(this.a), dynamicops.createString("below_noise"), dynamicops.createInt(this.b), dynamicops.createString("above_noise"), dynamicops.createInt(this.c))));
    }

    public static WorldGenFeatureDecoratorNoiseConfiguration a(Dynamic<?> dynamic) {
        double d0 = dynamic.get("noise_level").asDouble(0.0D);
        int i = dynamic.get("below_noise").asInt(0);
        int j = dynamic.get("above_noise").asInt(0);

        return new WorldGenFeatureDecoratorNoiseConfiguration(d0, i, j);
    }
}
