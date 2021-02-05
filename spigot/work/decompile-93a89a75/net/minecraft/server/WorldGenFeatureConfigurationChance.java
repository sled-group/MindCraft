package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureConfigurationChance implements WorldGenCarverConfiguration, WorldGenFeatureConfiguration {

    public final float b;

    public WorldGenFeatureConfigurationChance(float f) {
        this.b = f;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("probability"), dynamicops.createFloat(this.b))));
    }

    public static <T> WorldGenFeatureConfigurationChance a(Dynamic<T> dynamic) {
        float f = dynamic.get("probability").asFloat(0.0F);

        return new WorldGenFeatureConfigurationChance(f);
    }
}
