package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFeatureRandomChoice extends WorldGenerator<WorldGenFeatureRandomChoiceConfiguration> {

    public WorldGenFeatureRandomChoice(Function<Dynamic<?>, ? extends WorldGenFeatureRandomChoiceConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureRandomChoiceConfiguration worldgenfeaturerandomchoiceconfiguration) {
        Iterator iterator = worldgenfeaturerandomchoiceconfiguration.a.iterator();

        WorldGenFeatureRandomChoiceConfigurationWeight worldgenfeaturerandomchoiceconfigurationweight;

        do {
            if (!iterator.hasNext()) {
                return worldgenfeaturerandomchoiceconfiguration.b.a(generatoraccess, chunkgenerator, random, blockposition);
            }

            worldgenfeaturerandomchoiceconfigurationweight = (WorldGenFeatureRandomChoiceConfigurationWeight) iterator.next();
        } while (random.nextFloat() >= worldgenfeaturerandomchoiceconfigurationweight.c);

        return worldgenfeaturerandomchoiceconfigurationweight.a(generatoraccess, chunkgenerator, random, blockposition);
    }
}
