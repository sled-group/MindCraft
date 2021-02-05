package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenBuriedTreasureConfiguration implements WorldGenFeatureConfiguration {

    public final float a;

    public WorldGenBuriedTreasureConfiguration(float f) {
        this.a = f;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("probability"), dynamicops.createFloat(this.a))));
    }

    public static <T> WorldGenBuriedTreasureConfiguration a(Dynamic<T> dynamic) {
        float f = dynamic.get("probability").asFloat(0.0F);

        return new WorldGenBuriedTreasureConfiguration(f);
    }
}
