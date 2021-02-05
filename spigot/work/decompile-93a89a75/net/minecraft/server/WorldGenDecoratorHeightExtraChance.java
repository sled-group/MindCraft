package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorHeightExtraChance extends WorldGenDecorator<WorldGenDecoratorFrequencyExtraChanceConfiguration> {

    public WorldGenDecoratorHeightExtraChance(Function<Dynamic<?>, ? extends WorldGenDecoratorFrequencyExtraChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorFrequencyExtraChanceConfiguration worldgendecoratorfrequencyextrachanceconfiguration, BlockPosition blockposition) {
        int i = worldgendecoratorfrequencyextrachanceconfiguration.a;

        if (random.nextFloat() < worldgendecoratorfrequencyextrachanceconfiguration.b) {
            i += worldgendecoratorfrequencyextrachanceconfiguration.c;
        }

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(16);

            return generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition.b(k, 0, l));
        });
    }
}
