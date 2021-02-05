package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorLakeWater extends WorldGenDecorator<WorldGenDecoratorLakeChanceConfiguration> {

    public WorldGenDecoratorLakeWater(Function<Dynamic<?>, ? extends WorldGenDecoratorLakeChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorLakeChanceConfiguration worldgendecoratorlakechanceconfiguration, BlockPosition blockposition) {
        if (random.nextInt(worldgendecoratorlakechanceconfiguration.a) == 0) {
            int i = random.nextInt(16);
            int j = random.nextInt(chunkgenerator.getGenerationDepth());
            int k = random.nextInt(16);

            return Stream.of(blockposition.b(i, j, k));
        } else {
            return Stream.empty();
        }
    }
}
