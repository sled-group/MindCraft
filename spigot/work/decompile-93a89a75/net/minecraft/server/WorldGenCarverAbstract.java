package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.Dynamic;
import java.util.BitSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public abstract class WorldGenCarverAbstract<C extends WorldGenCarverConfiguration> {

    public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> a = a("cave", (WorldGenCarverAbstract) (new WorldGenCaves(WorldGenFeatureConfigurationChance::a, 256)));
    public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> b = a("hell_cave", (WorldGenCarverAbstract) (new WorldGenCavesHell(WorldGenFeatureConfigurationChance::a)));
    public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> c = a("canyon", (WorldGenCarverAbstract) (new WorldGenCanyon(WorldGenFeatureConfigurationChance::a)));
    public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> d = a("underwater_canyon", (WorldGenCarverAbstract) (new WorldGenCanyonOcean(WorldGenFeatureConfigurationChance::a)));
    public static final WorldGenCarverAbstract<WorldGenFeatureConfigurationChance> e = a("underwater_cave", (WorldGenCarverAbstract) (new WorldGenCavesOcean(WorldGenFeatureConfigurationChance::a)));
    protected static final IBlockData f = Blocks.AIR.getBlockData();
    protected static final IBlockData g = Blocks.CAVE_AIR.getBlockData();
    protected static final Fluid h = FluidTypes.WATER.i();
    protected static final Fluid i = FluidTypes.LAVA.i();
    protected Set<Block> j;
    protected Set<FluidType> k;
    private final Function<Dynamic<?>, ? extends C> m;
    protected final int l;

    private static <C extends WorldGenCarverConfiguration, F extends WorldGenCarverAbstract<C>> F a(String s, F f0) {
        return (WorldGenCarverAbstract) IRegistry.a(IRegistry.CARVER, s, (Object) f0);
    }

    public WorldGenCarverAbstract(Function<Dynamic<?>, ? extends C> function, int i) {
        this.j = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, new Block[]{Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.PACKED_ICE});
        this.k = ImmutableSet.of(FluidTypes.WATER);
        this.m = function;
        this.l = i;
    }

    public int c() {
        return 4;
    }

    protected boolean a(IChunkAccess ichunkaccess, long i, int j, int k, int l, double d0, double d1, double d2, double d3, double d4, BitSet bitset) {
        Random random = new Random(i + (long) k + (long) l);
        double d5 = (double) (k * 16 + 8);
        double d6 = (double) (l * 16 + 8);

        if (d0 >= d5 - 16.0D - d3 * 2.0D && d2 >= d6 - 16.0D - d3 * 2.0D && d0 <= d5 + 16.0D + d3 * 2.0D && d2 <= d6 + 16.0D + d3 * 2.0D) {
            int i1 = Math.max(MathHelper.floor(d0 - d3) - k * 16 - 1, 0);
            int j1 = Math.min(MathHelper.floor(d0 + d3) - k * 16 + 1, 16);
            int k1 = Math.max(MathHelper.floor(d1 - d4) - 1, 1);
            int l1 = Math.min(MathHelper.floor(d1 + d4) + 1, this.l - 8);
            int i2 = Math.max(MathHelper.floor(d2 - d3) - l * 16 - 1, 0);
            int j2 = Math.min(MathHelper.floor(d2 + d3) - l * 16 + 1, 16);

            if (this.a(ichunkaccess, k, l, i1, j1, k1, l1, i2, j2)) {
                return false;
            } else {
                boolean flag = false;
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();
                BlockPosition.MutableBlockPosition blockposition_mutableblockposition2 = new BlockPosition.MutableBlockPosition();

                for (int k2 = i1; k2 < j1; ++k2) {
                    int l2 = k2 + k * 16;
                    double d7 = ((double) l2 + 0.5D - d0) / d3;

                    for (int i3 = i2; i3 < j2; ++i3) {
                        int j3 = i3 + l * 16;
                        double d8 = ((double) j3 + 0.5D - d2) / d3;

                        if (d7 * d7 + d8 * d8 < 1.0D) {
                            AtomicBoolean atomicboolean = new AtomicBoolean(false);

                            for (int k3 = l1; k3 > k1; --k3) {
                                double d9 = ((double) k3 - 0.5D - d1) / d4;

                                if (!this.a(d7, d9, d8, k3)) {
                                    flag |= this.a(ichunkaccess, bitset, random, blockposition_mutableblockposition, blockposition_mutableblockposition1, blockposition_mutableblockposition2, j, k, l, l2, j3, k2, k3, i3, atomicboolean);
                                }
                            }
                        }
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    protected boolean a(IChunkAccess ichunkaccess, BitSet bitset, Random random, BlockPosition.MutableBlockPosition blockposition_mutableblockposition, BlockPosition.MutableBlockPosition blockposition_mutableblockposition1, BlockPosition.MutableBlockPosition blockposition_mutableblockposition2, int i, int j, int k, int l, int i1, int j1, int k1, int l1, AtomicBoolean atomicboolean) {
        int i2 = j1 | l1 << 4 | k1 << 8;

        if (bitset.get(i2)) {
            return false;
        } else {
            bitset.set(i2);
            blockposition_mutableblockposition.d(l, k1, i1);
            IBlockData iblockdata = ichunkaccess.getType(blockposition_mutableblockposition);
            IBlockData iblockdata1 = ichunkaccess.getType(blockposition_mutableblockposition1.g(blockposition_mutableblockposition).c(EnumDirection.UP));

            if (iblockdata.getBlock() == Blocks.GRASS_BLOCK || iblockdata.getBlock() == Blocks.MYCELIUM) {
                atomicboolean.set(true);
            }

            if (!this.a(iblockdata, iblockdata1)) {
                return false;
            } else {
                if (k1 < 11) {
                    ichunkaccess.setType(blockposition_mutableblockposition, WorldGenCarverAbstract.i.getBlockData(), false);
                } else {
                    ichunkaccess.setType(blockposition_mutableblockposition, WorldGenCarverAbstract.g, false);
                    if (atomicboolean.get()) {
                        blockposition_mutableblockposition2.g(blockposition_mutableblockposition).c(EnumDirection.DOWN);
                        if (ichunkaccess.getType(blockposition_mutableblockposition2).getBlock() == Blocks.DIRT) {
                            ichunkaccess.setType(blockposition_mutableblockposition2, ichunkaccess.getBiome(blockposition_mutableblockposition).q().a(), false);
                        }
                    }
                }

                return true;
            }
        }
    }

    public abstract boolean a(IChunkAccess ichunkaccess, Random random, int i, int j, int k, int l, int i1, BitSet bitset, C c0);

    public abstract boolean a(Random random, int i, int j, C c0);

    protected boolean a(IBlockData iblockdata) {
        return this.j.contains(iblockdata.getBlock());
    }

    protected boolean a(IBlockData iblockdata, IBlockData iblockdata1) {
        Block block = iblockdata.getBlock();

        return this.a(iblockdata) || (block == Blocks.SAND || block == Blocks.GRAVEL) && !iblockdata1.p().a(TagsFluid.WATER);
    }

    protected boolean a(IChunkAccess ichunkaccess, int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

        for (int i2 = k; i2 < l; ++i2) {
            for (int j2 = k1; j2 < l1; ++j2) {
                for (int k2 = i1 - 1; k2 <= j1 + 1; ++k2) {
                    if (this.k.contains(ichunkaccess.getFluid(blockposition_mutableblockposition.d(i2 + i * 16, k2, j2 + j * 16)).getType())) {
                        return true;
                    }

                    if (k2 != j1 + 1 && !this.a(k, l, k1, l1, i2, j2)) {
                        k2 = j1;
                    }
                }
            }
        }

        return false;
    }

    private boolean a(int i, int j, int k, int l, int i1, int j1) {
        return i1 == i || i1 == j - 1 || j1 == k || j1 == l - 1;
    }

    protected boolean a(int i, int j, double d0, double d1, int k, int l, float f) {
        double d2 = (double) (i * 16 + 8);
        double d3 = (double) (j * 16 + 8);
        double d4 = d0 - d2;
        double d5 = d1 - d3;
        double d6 = (double) (l - k);
        double d7 = (double) (f + 2.0F + 16.0F);

        return d4 * d4 + d5 * d5 - d6 * d6 <= d7 * d7;
    }

    protected abstract boolean a(double d0, double d1, double d2, int i);
}
