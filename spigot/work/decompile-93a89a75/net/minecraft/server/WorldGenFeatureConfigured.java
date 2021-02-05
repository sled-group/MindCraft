package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Random;

public class WorldGenFeatureConfigured<FC extends WorldGenFeatureConfiguration> {

    public final WorldGenerator<FC> a;
    public final FC b;

    public WorldGenFeatureConfigured(WorldGenerator<FC> worldgenerator, FC fc) {
        this.a = worldgenerator;
        this.b = fc;
    }

    public WorldGenFeatureConfigured(WorldGenerator<FC> worldgenerator, Dynamic<?> dynamic) {
        this(worldgenerator, worldgenerator.a(dynamic));
    }

    public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.createMap(ImmutableMap.of(dynamicops.createString("name"), dynamicops.createString(IRegistry.FEATURE.getKey(this.a).toString()), dynamicops.createString("config"), this.b.a(dynamicops).getValue())));
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition) {
        return this.a.generate(generatoraccess, chunkgenerator, random, blockposition, this.b);
    }

    public static <T> WorldGenFeatureConfigured<?> a(Dynamic<T> dynamic) {
        WorldGenerator<? extends WorldGenFeatureConfiguration> worldgenerator = (WorldGenerator) IRegistry.FEATURE.get(new MinecraftKey(dynamic.get("name").asString("")));

        return new WorldGenFeatureConfigured<>(worldgenerator, dynamic.get("config").orElseEmptyMap());
    }
}
