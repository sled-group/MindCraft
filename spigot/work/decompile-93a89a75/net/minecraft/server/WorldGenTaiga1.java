package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenTaiga1 extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.SPRUCE_LOG.getBlockData();
    private static final IBlockData aS = Blocks.SPRUCE_LEAVES.getBlockData();

    public WorldGenTaiga1(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function, false);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(5) + 7;
        int j = i - random.nextInt(2) - 3;
        int k = i - j;
        int l = 1 + random.nextInt(k + 1);

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            boolean flag = true;

            int i1;
            int j1;
            int k1;
            int l1;

            for (j1 = blockposition.getY(); j1 <= blockposition.getY() + 1 + i && flag; ++j1) {
                boolean flag1 = true;

                if (j1 - blockposition.getY() < j) {
                    i1 = 0;
                } else {
                    i1 = l;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (k1 = blockposition.getX() - i1; k1 <= blockposition.getX() + i1 && flag; ++k1) {
                    for (l1 = blockposition.getZ() - i1; l1 <= blockposition.getZ() + i1 && flag; ++l1) {
                        if (j1 >= 0 && j1 < 256) {
                            if (!a((VirtualLevelReadable) virtuallevelwritable, (BlockPosition) blockposition_mutableblockposition.d(k1, j1, l1))) {
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
            } else if (h(virtuallevelwritable, blockposition.down()) && blockposition.getY() < 256 - i - 1) {
                this.a(virtuallevelwritable, blockposition.down());
                j1 = 0;

                for (i1 = blockposition.getY() + i; i1 >= blockposition.getY() + j; --i1) {
                    for (int i2 = blockposition.getX() - j1; i2 <= blockposition.getX() + j1; ++i2) {
                        k1 = i2 - blockposition.getX();

                        for (l1 = blockposition.getZ() - j1; l1 <= blockposition.getZ() + j1; ++l1) {
                            int j2 = l1 - blockposition.getZ();

                            if (Math.abs(k1) != j1 || Math.abs(j2) != j1 || j1 <= 0) {
                                BlockPosition blockposition1 = new BlockPosition(i2, i1, l1);

                                if (g(virtuallevelwritable, blockposition1)) {
                                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, WorldGenTaiga1.aS, structureboundingbox);
                                }
                            }
                        }
                    }

                    if (j1 >= 1 && i1 == blockposition.getY() + j + 1) {
                        --j1;
                    } else if (j1 < l) {
                        ++j1;
                    }
                }

                for (i1 = 0; i1 < i - 1; ++i1) {
                    if (g(virtuallevelwritable, blockposition.up(i1))) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition.up(i1), WorldGenTaiga1.a, structureboundingbox);
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
