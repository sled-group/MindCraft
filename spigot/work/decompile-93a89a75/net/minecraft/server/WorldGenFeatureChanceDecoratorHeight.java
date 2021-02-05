package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenFeatureChanceDecoratorHeight extends WorldGenDecorator<WorldGenDecoratorFrequencyChanceConfiguration> {

    public WorldGenFeatureChanceDecoratorHeight(Function<Dynamic<?>, ? extends WorldGenDecoratorFrequencyChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorFrequencyChanceConfiguration worldgendecoratorfrequencychanceconfiguration, BlockPosition blockposition) {
        return IntStream.range(0, worldgendecoratorfrequencychanceconfiguration.a).filter((i) -> {
            return random.nextFloat() < worldgendecoratorfrequencychanceconfiguration.b;
        }).mapToObj((i) -> {
            int j = random.nextInt(16);
            int k = random.nextInt(16);
            int l = generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition.b(j, 0, k)).getY() * 2;

            if (l <= 0) {
                return null;
            } else {
                int i1 = random.nextInt(l);

                return blockposition.b(j, i1, k);
            }
        }).filter(Objects::nonNull);
    }
}
