package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class WorldGenFeatureRandomChoiceConfigurationWeight<FC extends WorldGenFeatureConfiguration> {

    public final WorldGenerator<FC> a;
    public final FC b;
    public final Float c;

    public WorldGenFeatureRandomChoiceConfigurationWeight(WorldGenerator<FC> worldgenerator, FC fc, Float ofloat) {
        this.a = worldgenerator;
        this.b = fc;
        this.c = ofloat;
    }

    public WorldGenFeatureRandomChoiceConfigurationWeight(WorldGenerator<FC> worldgenerator, Dynamic<?> dynamic, float f) {
        this(worldgenerator, worldgenerator.a(dynamic), f);
    }

    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("name"), dynamicops.createString(IRegistry.FEATURE.getKey(this.a).toString()), dynamicops.createString("config"), this.b.a(dynamicops).getValue(), dynamicops.createString("chance"), dynamicops.createFloat(this.c))));
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition) {
        return this.a.generate(generatoraccess, chunkgenerator, random, blockposition, this.b);
    }

    public static <T> WorldGenFeatureRandomChoiceConfigurationWeight<?> a(Dynamic<T> dynamic) {
        WorldGenerator<? extends WorldGenFeatureConfiguration> worldgenerator = (WorldGenerator) IRegistry.FEATURE.get(new MinecraftKey(dynamic.get("name").asString("")));

        return new WorldGenFeatureRandomChoiceConfigurationWeight<>(worldgenerator, dynamic.get("config").orElseEmptyMap(), dynamic.get("chance").asFloat(0.0F));
    }
}
