package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorSolidTopNoise extends WorldGenDecorator<WorldGenDecoratorNoiseConfiguration> {

    public WorldGenDecoratorSolidTopNoise(Function<Dynamic<?>, ? extends WorldGenDecoratorNoiseConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorNoiseConfiguration worldgendecoratornoiseconfiguration, BlockPosition blockposition) {
        double d0 = BiomeBase.e.a((double) blockposition.getX() / worldgendecoratornoiseconfiguration.b, (double) blockposition.getZ() / worldgendecoratornoiseconfiguration.b);
        int i = (int) Math.ceil((d0 + worldgendecoratornoiseconfiguration.c) * (double) worldgendecoratornoiseconfiguration.a);

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(16);
            int i1 = generatoraccess.a(worldgendecoratornoiseconfiguration.d, blockposition.getX() + k, blockposition.getZ() + l);

            return new BlockPosition(blockposition.getX() + k, i1, blockposition.getZ() + l);
        });
    }
}
