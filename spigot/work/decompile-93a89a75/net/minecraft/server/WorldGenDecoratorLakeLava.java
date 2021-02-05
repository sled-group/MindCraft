package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorLakeLava extends WorldGenDecorator<WorldGenDecoratorLakeChanceConfiguration> {

    public WorldGenDecoratorLakeLava(Function<Dynamic<?>, ? extends WorldGenDecoratorLakeChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorLakeChanceConfiguration worldgendecoratorlakechanceconfiguration, BlockPosition blockposition) {
        if (random.nextInt(worldgendecoratorlakechanceconfiguration.a / 10) == 0) {
            int i = random.nextInt(16);
            int j = random.nextInt(random.nextInt(chunkgenerator.getGenerationDepth() - 8) + 8);
            int k = random.nextInt(16);

            if (j < generatoraccess.getSeaLevel() || random.nextInt(worldgendecoratorlakechanceconfiguration.a / 8) == 0) {
                return Stream.of(blockposition.b(i, j, k));
            }
        }

        return Stream.empty();
    }
}
