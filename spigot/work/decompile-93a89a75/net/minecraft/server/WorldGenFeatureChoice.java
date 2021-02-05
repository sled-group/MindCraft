package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureChoice extends WorldGenerator<WorldGenFeatureChoiceConfiguration> {

    public WorldGenFeatureChoice(Function<Dynamic<?>, ? extends WorldGenFeatureChoiceConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureChoiceConfiguration worldgenfeaturechoiceconfiguration) {
        boolean flag = random.nextBoolean();

        return flag ? worldgenfeaturechoiceconfiguration.a.a(generatoraccess, chunkgenerator, random, blockposition) : worldgenfeaturechoiceconfiguration.b.a(generatoraccess, chunkgenerator, random, blockposition);
    }
}
