package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldGenDecoratorCarveMask extends WorldGenDecorator<WorldGenDecoratorCarveMaskConfiguration> {

    public WorldGenDecoratorCarveMask(Function<Dynamic<?>, ? extends WorldGenDecoratorCarveMaskConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorCarveMaskConfiguration worldgendecoratorcarvemaskconfiguration, BlockPosition blockposition) {
        IChunkAccess ichunkaccess = generatoraccess.w(blockposition);
        ChunkCoordIntPair chunkcoordintpair = ichunkaccess.getPos();
        BitSet bitset = ichunkaccess.a(worldgendecoratorcarvemaskconfiguration.a);

        return IntStream.range(0, bitset.length()).filter((i) -> {
            return bitset.get(i) && random.nextFloat() < worldgendecoratorcarvemaskconfiguration.b;
        }).mapToObj((i) -> {
            int j = i & 15;
            int k = i >> 4 & 15;
            int l = i >> 8;

            return new BlockPosition(chunkcoordintpair.d() + j, l, chunkcoordintpair.e() + k);
        });
    }
}
