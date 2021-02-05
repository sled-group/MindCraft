package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureOceanRuinConfiguration implements WorldGenFeatureConfiguration {

    public final WorldGenFeatureOceanRuin.Temperature a;
    public final float b;
    public final float c;

    public WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature worldgenfeatureoceanruin_temperature, float f, float f1) {
        this.a = worldgenfeatureoceanruin_temperature;
        this.b = f;
        this.c = f1;
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("biome_temp"), dynamicops.createString(this.a.a()), dynamicops.createString("large_probability"), dynamicops.createFloat(this.b), dynamicops.createString("cluster_probability"), dynamicops.createFloat(this.c))));
    }

    public static <T> WorldGenFeatureOceanRuinConfiguration a(Dynamic<T> dynamic) {
        WorldGenFeatureOceanRuin.Temperature worldgenfeatureoceanruin_temperature = WorldGenFeatureOceanRuin.Temperature.a(dynamic.get("biome_temp").asString(""));
        float f = dynamic.get("large_probability").asFloat(0.0F);
        float f1 = dynamic.get("cluster_probability").asFloat(0.0F);

        return new WorldGenFeatureOceanRuinConfiguration(worldgenfeatureoceanruin_temperature, f, f1);
    }
}
