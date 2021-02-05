package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureRandom extends WorldGenerator<WorldGenFeatureRandomConfiguration> {

    public WorldGenFeatureRandom(Function<Dynamic<?>, ? extends WorldGenFeatureRandomConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureRandomConfiguration worldgenfeaturerandomconfiguration) {
        int i = random.nextInt(5) - 3 + worldgenfeaturerandomconfiguration.b;

        for (int j = 0; j < i; ++j) {
            int k = random.nextInt(worldgenfeaturerandomconfiguration.a.size());
            WorldGenFeatureConfigured<?> worldgenfeatureconfigured = (WorldGenFeatureConfigured) worldgenfeaturerandomconfiguration.a.get(k);

            worldgenfeatureconfigured.a(generatoraccess, chunkgenerator, random, blockposition);
        }

        return true;
    }
}
