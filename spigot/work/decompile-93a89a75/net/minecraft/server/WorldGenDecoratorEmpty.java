package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorEmpty extends WorldGenDecoratorFeatureSimple<WorldGenFeatureDecoratorEmptyConfiguration> {

    public WorldGenDecoratorEmpty(Function<Dynamic<?>, ? extends WorldGenFeatureDecoratorEmptyConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(Random random, WorldGenFeatureDecoratorEmptyConfiguration worldgenfeaturedecoratoremptyconfiguration, BlockPosition blockposition) {
        return Stream.of(blockposition);
    }
}
