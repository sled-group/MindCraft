package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenSurfaceMesaForest extends WorldGenSurfaceMesa {

    private static final IBlockData S = Blocks.WHITE_TERRACOTTA.getBlockData();
    private static final IBlockData T = Blocks.ORANGE_TERRACOTTA.getBlockData();
    private static final IBlockData U = Blocks.TERRACOTTA.getBlockData();

    public WorldGenSurfaceMesaForest(Function<Dynamic<?>, ? extends WorldGenSurfaceConfigurationBase> function) {
        super(function);
    }

    @Override
    public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, WorldGenSurfaceConfigurationBase worldgensurfaceconfigurationbase) {
        int j1 = i & 15;
        int k1 = j & 15;
        IBlockData iblockdata2 = WorldGenSurfaceMesaForest.S;
        IBlockData iblockdata3 = biomebase.q().b();
        int l1 = (int) (d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        boolean flag = Math.cos(d0 / 3.0D * 3.141592653589793D) > 0.0D;
        int i2 = -1;
        boolean flag1 = false;
        int j2 = 0;
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

        for (int k2 = k; k2 >= 0; --k2) {
            if (j2 < 15) {
                blockposition_mutableblockposition.d(j1, k2, k1);
                IBlockData iblockdata4 = ichunkaccess.getType(blockposition_mutableblockposition);

                if (iblockdata4.isAir()) {
                    i2 = -1;
                } else if (iblockdata4.getBlock() == iblockdata.getBlock()) {
                    if (i2 == -1) {
                        flag1 = false;
                        if (l1 <= 0) {
                            iblockdata2 = Blocks.AIR.getBlockData();
                            iblockdata3 = iblockdata;
                        } else if (k2 >= l - 4 && k2 <= l + 1) {
                            iblockdata2 = WorldGenSurfaceMesaForest.S;
                            iblockdata3 = biomebase.q().b();
                        }

                        if (k2 < l && (iblockdata2 == null || iblockdata2.isAir())) {
                            iblockdata2 = iblockdata1;
                        }

                        i2 = l1 + Math.max(0, k2 - l);
                        if (k2 >= l - 1) {
                            if (k2 > 86 + l1 * 2) {
                                if (flag) {
                                    ichunkaccess.setType(blockposition_mutableblockposition, Blocks.COARSE_DIRT.getBlockData(), false);
                                } else {
                                    ichunkaccess.setType(blockposition_mutableblockposition, Blocks.GRASS_BLOCK.getBlockData(), false);
                                }
                            } else if (k2 > l + 3 + l1) {
                                IBlockData iblockdata5;

                                if (k2 >= 64 && k2 <= 127) {
                                    if (flag) {
                                        iblockdata5 = WorldGenSurfaceMesaForest.U;
                                    } else {
                                        iblockdata5 = this.a(i, k2, j);
                                    }
                                } else {
                                    iblockdata5 = WorldGenSurfaceMesaForest.T;
                                }

                                ichunkaccess.setType(blockposition_mutableblockposition, iblockdata5, false);
                            } else {
                                ichunkaccess.setType(blockposition_mutableblockposition, biomebase.q().a(), false);
                                flag1 = true;
                            }
                        } else {
                            ichunkaccess.setType(blockposition_mutableblockposition, iblockdata3, false);
                            if (iblockdata3 == WorldGenSurfaceMesaForest.S) {
                                ichunkaccess.setType(blockposition_mutableblockposition, WorldGenSurfaceMesaForest.T, false);
                            }
                        }
                    } else if (i2 > 0) {
                        --i2;
                        if (flag1) {
                            ichunkaccess.setType(blockposition_mutableblockposition, WorldGenSurfaceMesaForest.T, false);
                        } else {
                            ichunkaccess.setType(blockposition_mutableblockposition, this.a(i, k2, j), false);
                        }
                    }

                    ++j2;
                }
            }
        }

    }
}
