package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorEndGateway extends WorldGenDecorator<WorldGenFeatureDecoratorEmptyConfiguration> {

    public WorldGenDecoratorEndGateway(Function<Dynamic<?>, ? extends WorldGenFeatureDecoratorEmptyConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenFeatureDecoratorEmptyConfiguration worldgenfeaturedecoratoremptyconfiguration, BlockPosition blockposition) {
        if (random.nextInt(700) == 0) {
            int i = random.nextInt(16);
            int j = random.nextInt(16);
            int k = generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, blockposition.b(i, 0, j)).getY();

            if (k > 0) {
                int l = k + 3 + random.nextInt(7);

                return Stream.of(blockposition.b(i, l, j));
            }
        }

        return Stream.empty();
    }
}
