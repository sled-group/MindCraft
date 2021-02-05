package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenTaiga2 extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.SPRUCE_LOG.getBlockData();
    private static final IBlockData aS = Blocks.SPRUCE_LEAVES.getBlockData();

    public WorldGenTaiga2(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag) {
        super(function, flag);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(4) + 6;
        int j = 1 + random.nextInt(2);
        int k = i - j;
        int l = 2 + random.nextInt(2);
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int i1;
            int j1;
            int k1;
            int l1;

            for (j1 = blockposition.getY(); j1 <= blockposition.getY() + 1 + i && flag; ++j1) {
                if (j1 - blockposition.getY() < j) {
                    i1 = 0;
                } else {
                    i1 = l;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (k1 = blockposition.getX() - i1; k1 <= blockposition.getX() + i1 && flag; ++k1) {
                    for (l1 = blockposition.getZ() - i1; l1 <= blockposition.getZ() + i1 && flag; ++l1) {
                        if (j1 >= 0 && j1 < 256) {
                            blockposition_mutableblockposition.d(k1, j1, l1);
                            if (!g(virtuallevelwritable, blockposition_mutableblockposition)) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            } else if (i(virtuallevelwritable, blockposition.down()) && blockposition.getY() < 256 - i - 1) {
                this.a(virtuallevelwritable, blockposition.down());
                j1 = random.nextInt(2);
                i1 = 1;
                byte b0 = 0;

                for (k1 = 0; k1 <= k; ++k1) {
                    l1 = blockposition.getY() + i - k1;

                    for (int i2 = blockposition.getX() - j1; i2 <= blockposition.getX() + j1; ++i2) {
                        int j2 = i2 - blockposition.getX();

                        for (int k2 = blockposition.getZ() - j1; k2 <= blockposition.getZ() + j1; ++k2) {
                            int l2 = k2 - blockposition.getZ();

                            if (Math.abs(j2) != j1 || Math.abs(l2) != j1 || j1 <= 0) {
                                BlockPosition blockposition1 = new BlockPosition(i2, l1, k2);

                                if (g(virtuallevelwritable, blockposition1) || j(virtuallevelwritable, blockposition1)) {
                                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, WorldGenTaiga2.aS, structureboundingbox);
                                }
                            }
                        }
                    }

                    if (j1 >= i1) {
                        j1 = b0;
                        b0 = 1;
                        ++i1;
                        if (i1 > l) {
                            i1 = l;
                        }
                    } else {
                        ++j1;
                    }
                }

                k1 = random.nextInt(3);

                for (l1 = 0; l1 < i - k1; ++l1) {
                    if (g(virtuallevelwritable, blockposition.up(l1))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.up(l1), WorldGenTaiga2.a, structureboundingbox);
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
