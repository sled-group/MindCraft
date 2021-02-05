package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorDungeon extends WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> {

    public WorldGenDecoratorDungeon(Function<Dynamic<?>, ? extends WorldGenDecoratorDungeonConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorDungeonConfiguration worldgendecoratordungeonconfiguration, BlockPosition blockposition) {
        int i = worldgendecoratordungeonconfiguration.a;

        return IntStream.range(0, i).mapToObj((j) -> {
            int k = random.nextInt(16);
            int l = random.nextInt(chunkgenerator.getGenerationDepth());
            int i1 = random.nextInt(16);

            return blockposition.b(k, l, i1);
        });
    }
}
