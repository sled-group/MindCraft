package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorSkyVisibleBiased extends WorldGenDecorator<WorldGenDecoratorChanceConfiguration> {

    public WorldGenDecoratorSkyVisibleBiased(Function<Dynamic<?>, ? extends WorldGenDecoratorChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorChanceConfiguration worldgendecoratorchanceconfiguration, BlockPosition blockposition) {
        if (random.nextFloat() < 1.0F / (float) worldgendecoratorchanceconfiguration.a) {
            int i = random.nextInt(16);
            int j = random.nextInt(16);
            int k = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR_WG, blockposition.getX() + i, blockposition.getZ() + j);

            return Stream.of(new BlockPosition(blockposition.getX() + i, k, blockposition.getZ() + j));
        } else {
            return Stream.empty();
        }
    }
}
