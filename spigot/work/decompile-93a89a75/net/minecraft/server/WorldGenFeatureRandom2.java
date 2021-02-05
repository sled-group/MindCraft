package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorldGenFeatureRandom2 implements WorldGenFeatureConfiguration {

    public final List<WorldGenFeatureConfigured<?>> a;

    public WorldGenFeatureRandom2(List<WorldGenFeatureConfigured<?>> list) {
        this.a = list;
    }

    public WorldGenFeatureRandom2(WorldGenerator<?>[] aworldgenerator, WorldGenFeatureConfiguration[] aworldgenfeatureconfiguration) {
        this((List) IntStream.range(0, aworldgenerator.length).mapToObj((i) -> {
            return a(aworldgenerator[i], aworldgenfeatureconfiguration[i]);
        }).collect(Collectors.toList()));
    }

    private static <FC extends WorldGenFeatureConfiguration> WorldGenFeatureConfigured<FC> a(WorldGenerator<FC> worldgenerator, WorldGenFeatureConfiguration worldgenfeatureconfiguration) {
        return new WorldGenFeatureConfigured<>(worldgenerator, worldgenfeatureconfiguration);
    }

    @Override
    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("features"), dynamicops.createList(this.a.stream().map((worldgenfeatureconfigured) -> {
            return worldgenfeatureconfigured.a(dynamicops).getValue();
        })))));
    }

    public static <T> WorldGenFeatureRandom2 a(Dynamic<T> dynamic) {
        List<WorldGenFeatureConfigured<?>> list = dynamic.get("features").asList(WorldGenFeatureConfigured::a);

        return new WorldGenFeatureRandom2(list);
    }
}
