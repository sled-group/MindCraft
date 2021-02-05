package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class WorldGenCavesOcean extends WorldGenCaves {

    public WorldGenCavesOcean(Function<Dynamic<?>, ? extends WorldGenFeatureConfigurationChance> function) {
        super(function, 256);
        this.j = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, new Block[]{Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.SAND, Blocks.GRAVEL, Blocks.WATER, Blocks.LAVA, Blocks.OBSIDIAN, Blocks.AIR, Blocks.CAVE_AIR, Blocks.PACKED_ICE});
    }

    @Override
    protected boolean a(IChunkAccess ichunkaccess, int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        return false;
    }

    @Override
    protected boolean a(IChunkAccess ichunkaccess, BitSet bitset, Random random, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, BlockPosition.MutableBlockPosition blockposition_mutableblockposition1, BlockPosition.MutableBlockPosition blockposition_mutableblockposition2, int i, int j, int k, int l, int i1, int j1, int k1, int l1, AtomicBoolean atomicboolean) {
        return a(this, ichunkaccess, bitset, random, blockposition_mutableblockposition, i, j, k, l, i1, j1, k1, l1);
    }

    protected static boolean a(WorldGenCarverAbstract<?> worldgencarverabstract, IChunkAccess ichunkaccess, BitSet bitset, Random random, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        if (k1 >= i) {
            return false;
        } else {
            int i2 = j1 | l1 << 4 | k1 << 8;

            if (bitset.get(i2)) {
                return false;
            } else {
                bitset.set(i2);
                blockposition_mutableblockposition.d(l, k1, i1);
                IBlockData iblockdata = ichunkaccess.getType(blockposition_mutableblockposition);

                if (!worldgencarverabstract.a(iblockdata)) {
                    return false;
                } else if (k1 == 10) {
                    float f = random.nextFloat();

                    if ((double) f < 0.25D) {
                        ichunkaccess.setType(blockposition_mutableblockposition, Blocks.MAGMA_BLOCK.getBlockData(), false);
                        ichunkaccess.n().a(blockposition_mutableblockposition, Blocks.MAGMA_BLOCK, 0);
                    } else {
                        ichunkaccess.setType(blockposition_mutableblockposition, Blocks.OBSIDIAN.getBlockData(), false);
                    }

                    return true;
                } else if (k1 < 10) {
                    ichunkaccess.setType(blockposition_mutableblockposition, Blocks.LAVA.getBlockData(), false);
                    return false;
                } else {
                    boolean flag = false;
                    Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                    while (iterator.hasNext()) {
                        EnumDirection enumdirection = (EnumDirection) iterator.next();
                        int j2 = l + enumdirection.getAdjacentX();
                        int k2 = i1 + enumdirection.getAdjacentZ();

                        if (j2 >> 4 != j || k2 >> 4 != k || ichunkaccess.getType(blockposition_mutableblockposition.d(j2, k1, k2)).isAir()) {
                            ichunkaccess.setType(blockposition_mutableblockposition, WorldGenCavesOcean.h.getBlockData(), false);
                            ichunkaccess.o().a(blockposition_mutableblockposition, WorldGenCavesOcean.h.getType(), 0);
                            flag = true;
                            break;
                        }
                    }

                    blockposition_mutableblockposition.d(l, k1, i1);
                    if (!flag) {
                        ichunkaccess.setType(blockposition_mutableblockposition, WorldGenCavesOcean.h.getBlockData(), false);
                        return true;
                    } else {
                        return true;
                    }
                }
            }
        }
    }
}
