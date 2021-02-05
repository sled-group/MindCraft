package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorNoiseHeight32 extends WorldGenDecorator<WorldGenFeatureDecoratorNoiseConfiguration> {

    public WorldGenDecoratorNoiseHeight32(Function<Dynamic<?>, ? extends WorldGenFeatureDecoratorNoiseConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenFeatureDecoratorNoiseConfiguration worldgenfeaturedecoratornoiseconfiguration, BlockPosition blockposition) {
        double d0 = BiomeBase.e.a((double) blockposition.getX() / 200.0D, (double) blockposition.getZ() / 200.0D);
        int i = d0 < worldgenfeaturedecoratornoiseconfiguration.a ? worldgenfeaturedecoratornoiseconfiguration.b : worldgenfeaturedecoratornoiseconfiguration.c;

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(16);
            int i1 = generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition.b(k, 0, l)).getY() + 32;

            if (i1 <= 0) {
                return null;
            } else {
                int j1 = random.nextInt(i1);

                return blockposition.b(k, j1, l);
            }
        }).filter(Objects::nonNull);
    }
}
