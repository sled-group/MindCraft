package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeaturePillagerOutpostConfiguration implements WorldGenFeatureConfiguration {

    public final double a;

    public WorldGenFeaturePillagerOutpostConfiguration(double d0) {
        this.a = d0;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("probability"), dynamicops.createDouble(this.a))));
    }

    public static <T> WorldGenFeaturePillagerOutpostConfiguration a(Dynamic<T> dynamic) {
        float f = dynamic.get("probability").asFloat(0.0F);

        return new WorldGenFeaturePillagerOutpostConfiguration((double) f);
    }
}
