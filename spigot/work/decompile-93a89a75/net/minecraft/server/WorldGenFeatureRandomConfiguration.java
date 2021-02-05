package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorldGenFeatureRandomConfiguration implements WorldGenFeatureConfiguration {

    public final List<WorldGenFeatureConfigured<?>> a;
    public final int b;

    public WorldGenFeatureRandomConfiguration(List<WorldGenFeatureConfigured<?>> list, int i) {
        this.a = list;
        this.b = i;
    }

    public WorldGenFeatureRandomConfiguration(WorldGenerator<?>[] aworldgenerator, WorldGenFeatureConfiguration[] aworldgenfeatureconfiguration, int i) {
        this((List) IntStream.range(0, aworldgenerator.length).mapToObj((j) -> {
            return a(aworldgenerator[j], aworldgenfeatureconfiguration[j]);
        }).collect(Collectors.toList()), i);
    }

    private static <FC extends WorldGenFeatureConfiguration> WorldGenFeatureConfigured<?> a(WorldGenerator<FC> worldgenerator, WorldGenFeatureConfiguration worldgenfeatureconfiguration) {
        return new WorldGenFeatureConfigured<>(worldgenerator, worldgenfeatureconfiguration);
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("features"), dynamicops.createList(this.a.stream().map((worldgenfeatureconfigured) -> {
            return worldgenfeatureconfigured.a(dynamicops).getValue();
        })), dynamicops.createString("count"), dynamicops.createInt(this.b))));
    }

    public static <T> WorldGenFeatureRandomConfiguration a(Dynamic<T> dynamic) {
        List<WorldGenFeatureConfigured<?>> list = dynamic.get("features").asList(WorldGenFeatureConfigured::a);
        int i = dynamic.get("count").asInt(0);

        return new WorldGenFeatureRandomConfiguration(list, i);
    }
}
