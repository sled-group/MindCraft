package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorSolidTopHeight extends WorldGenDecorator<WorldGenDecoratorRangeConfiguration> {

    public WorldGenDecoratorSolidTopHeight(Function<Dynamic<?>, ? extends WorldGenDecoratorRangeConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorRangeConfiguration worldgendecoratorrangeconfiguration, BlockPosition blockposition) {
        int i = random.nextInt(worldgendecoratorrangeconfiguration.b - worldgendecoratorrangeconfiguration.a) + worldgendecoratorrangeconfiguration.a;

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(16);
            int i1 = generatoraccess.a(HeightMap.Type.OCEAN_FLOOR_WG, blockposition.getX() + k, blockposition.getZ() + l);

            return new BlockPosition(blockposition.getX() + k, i1, blockposition.getZ() + l);
        });
    }
}
