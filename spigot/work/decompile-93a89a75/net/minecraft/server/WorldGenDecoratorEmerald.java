package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorEmerald extends WorldGenDecoratorFeatureSimple<WorldGenFeatureDecoratorEmptyConfiguration> {

    public WorldGenDecoratorEmerald(Function<Dynamic<?>, ? extends WorldGenFeatureDecoratorEmptyConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(Random random, WorldGenFeatureDecoratorEmptyConfiguration worldgenfeaturedecoratoremptyconfiguration, BlockPosition blockposition) {
        int i = 3 + random.nextInt(6);

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(28) + 4;
            int i1 = random.nextInt(16);

            return blockposition.b(k, l, i1);
        });
    }
}
