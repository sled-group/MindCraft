package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureRandom2Configuration extends WorldGenerator<WorldGenFeatureRandom2> {

    public WorldGenFeatureRandom2Configuration(Function<Dynamic<?>, ? extends WorldGenFeatureRandom2> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureRandom2 worldgenfeaturerandom2) {
        int i = random.nextInt(worldgenfeaturerandom2.a.size());
        WorldGenFeatureConfigured<?> worldgenfeatureconfigured = (WorldGenFeatureConfigured) worldgenfeaturerandom2.a.get(i);

        return worldgenfeatureconfigured.a(generatoraccess, chunkgenerator, random, blockposition);
    }
}
