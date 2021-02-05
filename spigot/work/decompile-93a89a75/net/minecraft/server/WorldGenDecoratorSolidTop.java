package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorSolidTop extends WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> {

    public WorldGenDecoratorSolidTop(Function<Dynamic<?>, ? extends WorldGenFeatureDecoratorEmptyConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenFeatureDecoratorEmptyConfiguration worldgenfeaturedecoratoremptyconfiguration, BlockPosition blockposition) {
        int i = random.nextInt(16);
        int j = random.nextInt(16);
        int k = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR_WG, blockposition.getX() + i, blockposition.getZ() + j);

        return Stream.of(new BlockPosition(blockposition.getX() + i, k, blockposition.getZ() + j));
    }
}
