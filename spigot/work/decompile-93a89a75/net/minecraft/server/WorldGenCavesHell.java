package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class WorldGenCavesHell extends WorldGenCaves {

    public WorldGenCavesHell(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> function) {
        super(function, 128);
        this.j = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, new Block[]{Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.NETHERRACK});
        this.k = ImmutableSet.of(FluidTypes.LAVA, FluidTypes.WATER);
    }

    @Override
    protected int a() {
        return 10;
    }

    @Override
    protected float a(Random random) {
        return (random.nextFloat() * 2.0F + random.nextFloat()) * 2.0F;
    }

    @Override
    protected double b() {
        return 5.0D;
    }

    @Override
    protected int b(Random random) {
        return random.nextInt(this.l);
    }

    @Override
    protected boolean a(IChunkAccess ichunkaccess, BitSet bitset, Random random, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, BlockPosition.MutableBlockPosition blockposition_mutableblockposition1, BlockPosition.MutableBlockPosition blockposition_mutableblockposition2, int i, int j, int k, int l, int i1, int j1, int k1, int l1, AtomicBoolean atomicboolean) {
        int i2 = j1 | l1 << 4 | k1 << 8;

        if (bitset.get(i2)) {
            return false;
        } else {
            bitset.set(i2);
            blockposition_mutableblockposition.d(l, k1, i1);
            if (this.a(ichunkaccess.getType(blockposition_mutableblockposition))) {
                IBlockData iblockdata;

                if (k1 <= 31) {
                    iblockdata = WorldGenCavesHell.i.getBlockData();
                } else {
                    iblockdata = WorldGenCavesHell.g;
                }

                ichunkaccess.setType(blockposition_mutableblockposition, iblockdata, false);
                return true;
            } else {
                return false;
            }
        }
    }
}
