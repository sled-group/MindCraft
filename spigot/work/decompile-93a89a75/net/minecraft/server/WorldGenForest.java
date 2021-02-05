package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenForest extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.BIRCH_LOG.getBlockData();
    private static final IBlockData aS = Blocks.BIRCH_LEAVES.getBlockData();
    private final boolean aT;

    public WorldGenForest(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag, boolean flag1) {
        super(function, flag);
        this.aT = flag1;
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(3) + 5;

        if (this.aT) {
            i += random.nextInt(7);
        }

        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int j;
            int k;
            int l;

            for (j = blockposition.getY(); j <= blockposition.getY() + 1 + i; ++j) {
                byte b0 = 1;

                if (j == blockposition.getY()) {
                    b0 = 0;
                }

                if (j >= blockposition.getY() + 1 + i - 2) {
                    b0 = 2;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (k = blockposition.getX() - b0; k <= blockposition.getX() + b0 && flag; ++k) {
                    for (l = blockposition.getZ() - b0; l <= blockposition.getZ() + b0 && flag; ++l) {
                        if (j >= 0 && j < 256) {
                            if (!a((VirtualLevelReadable) virtuallevelwritable, (BlockPosition) blockposition_mutableblockposition.d(k, j, l))) {
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

                for (j = blockposition.getY() - 3 + i; j <= blockposition.getY() + i; ++j) {
                    int i1 = j - (blockposition.getY() + i);
                    int j1 = 1 - i1 / 2;

                    for (k = blockposition.getX() - j1; k <= blockposition.getX() + j1; ++k) {
                        l = k - blockposition.getX();

                        for (int k1 = blockposition.getZ() - j1; k1 <= blockposition.getZ() + j1; ++k1) {
                            int l1 = k1 - blockposition.getZ();

                            if (Math.abs(l) != j1 || Math.abs(l1) != j1 || random.nextInt(2) != 0 && i1 != 0) {
                                BlockPosition blockposition1 = new BlockPosition(k, j, k1);

                                if (g(virtuallevelwritable, blockposition1)) {
                                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, WorldGenForest.aS, structureboundingbox);
                                }
                            }
                        }
                    }
                }

                for (j = 0; j < i; ++j) {
                    if (g(virtuallevelwritable, blockposition.up(j))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.up(j), WorldGenForest.a, structureboundingbox);
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
