package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorNetherRandomCount extends WorldGenDecoratorFeatureSimple<WorldGenFeatureChanceDecoratorCountConfiguration> {

    public WorldGenDecoratorNetherRandomCount(Function<Dynamic<?>, ? extends WorldGenFeatureChanceDecoratorCountConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(Random random, WorldGenFeatureChanceDecoratorCountConfiguration worldgenfeaturechancedecoratorcountconfiguration, BlockPosition blockposition) {
        int i = random.nextInt(Math.max(worldgenfeaturechancedecoratorcountconfiguration.a, 1));

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(worldgenfeaturechancedecoratorcountconfiguration.d - worldgenfeaturechancedecoratorcountconfiguration.c) + worldgenfeaturechancedecoratorcountconfiguration.b;
            int i1 = random.nextInt(16);

            return blockposition.b(k, l, i1);
        });
    }
}
