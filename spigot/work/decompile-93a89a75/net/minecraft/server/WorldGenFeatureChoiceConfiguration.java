package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class WorldGenFeatureChoiceConfiguration implements WorldGenFeatureConfiguration {

    public final WorldGenFeatureConfigured<?> a;
    public final WorldGenFeatureConfigured<?> b;

    public WorldGenFeatureChoiceConfiguration(WorldGenFeatureConfigured<?> worldgenfeatureconfigured, WorldGenFeatureConfigured<?> worldgenfeatureconfigured1) {
        this.a = worldgenfeatureconfigured;
        this.b = worldgenfeatureconfigured1;
    }

    public WorldGenFeatureChoiceConfiguration(WorldGenerator<?> worldgenerator, WorldGenFeatureConfiguration worldgenfeatureconfiguration, WorldGenerator<?> worldgenerator1, WorldGenFeatureConfiguration worldgenfeatureconfiguration1) {
        this(a(worldgenerator, worldgenfeatureconfiguration), a(worldgenerator1, worldgenfeatureconfiguration1));
    }

    private static <FC extends WorldGenFeatureConfiguration> WorldGenFeatureConfigured<FC> a(WorldGenerator<FC> worldgenerator, WorldGenFeatureConfiguration worldgenfeatureconfiguration) {
        return new WorldGenFeatureConfigured<>(worldgenerator, worldgenfeatureconfiguration);
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("feature_true"), this.a.a(dynamicops).getValue(), dynamicops.createString("feature_false"), this.b.a(dynamicops).getValue())));
    }

    public static <T> WorldGenFeatureChoiceConfiguration a(Dynamic<T> dynamic) {
        WorldGenFeatureConfigured<?> worldgenfeatureconfigured = WorldGenFeatureConfigured.a(dynamic.get("feature_true").orElseEmptyMap());
        WorldGenFeatureConfigured<?> worldgenfeatureconfigured1 = WorldGenFeatureConfigured.a(dynamic.get("feature_false").orElseEmptyMap());

        return new WorldGenFeatureChoiceConfiguration(worldgenfeatureconfigured, worldgenfeatureconfigured1);
    }
}
