package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorChancePass extends WorldGenDecoratorFeatureSimple<WorldGenDecoratorChanceConfiguration> {

    public WorldGenDecoratorChancePass(Function<Dynamic<?>, ? extends WorldGenDecoratorChanceConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(Random random, WorldGenDecoratorChanceConfiguration worldgendecoratorchanceconfiguration, BlockPosition blockposition) {
        return random.nextFloat() < 1.0F / (float) worldgendecoratorchanceconfiguration.a ? Stream.of(blockposition) : Stream.empty();
    }
}
