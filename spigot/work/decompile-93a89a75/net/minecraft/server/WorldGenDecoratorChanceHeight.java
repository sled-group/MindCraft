package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorChanceHeight extends WorldGenDecorator<WorldGenDecoratorChanceConfiguration> {

    public WorldGenDecoratorChanceHeight(Function<Dynamic<?>, ? extends WorldGenDecoratorChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorChanceConfiguration worldgendecoratorchanceconfiguration, BlockPosition blockposition) {
        if (random.nextFloat() < 1.0F / (float) worldgendecoratorchanceconfiguration.a) {
            int i = random.nextInt(16);
            int j = random.nextInt(16);
            int k = generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition.b(i, 0, j)).getY() * 2;

            if (k <= 0) {
                return Stream.empty();
            } else {
                int l = random.nextInt(k);

                return Stream.of(blockposition.b(i, l, j));
            }
        } else {
            return Stream.empty();
        }
    }
}
