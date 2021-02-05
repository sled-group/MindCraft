package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenSwampTree extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.OAK_LOG.getBlockData();
    private static final IBlockData aS = Blocks.OAK_LEAVES.getBlockData();

    public WorldGenSwampTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function, false);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(4) + 5;

        blockposition = virtuallevelwritable.getHighestBlockYAt(HeightMap.Type.OCEAN_FLOOR, blockposition);
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
                    b0 = 3;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (k = blockposition.getX() - b0; k <= blockposition.getX() + b0 && flag; ++k) {
                    for (l = blockposition.getZ() - b0; l <= blockposition.getZ() + b0 && flag; ++l) {
                        if (j >= 0 && j < 256) {
                            blockposition_mutableblockposition.d(k, j, l);
                            if (!g(virtuallevelwritable, blockposition_mutableblockposition)) {
                                if (e(virtuallevelwritable, blockposition_mutableblockposition)) {
                                    if (j > blockposition.getY()) {
                                        flag = false;
                                    }
                                } else {
                                    flag = false;
                                }
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

                int i1;
                int j1;
                int k1;
                BlockPosition blockposition1;

                for (j = blockposition.getY() - 3 + i; j <= blockposition.getY() + i; ++j) {
                    i1 = j - (blockposition.getY() + i);
                    j1 = 2 - i1 / 2;

                    for (k = blockposition.getX() - j1; k <= blockposition.getX() + j1; ++k) {
                        l = k - blockposition.getX();

                        for (k1 = blockposition.getZ() - j1; k1 <= blockposition.getZ() + j1; ++k1) {
                            int l1 = k1 - blockposition.getZ();

                            if (Math.abs(l) != j1 || Math.abs(l1) != j1 || random.nextInt(2) != 0 && i1 != 0) {
                                blockposition1 = new BlockPosition(k, j, k1);
                                if (g(virtuallevelwritable, blockposition1) || j(virtuallevelwritable, blockposition1)) {
                                    this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, WorldGenSwampTree.aS, structureboundingbox);
                                }
                            }
                        }
                    }
                }

                for (j = 0; j < i; ++j) {
                    BlockPosition blockposition2 = blockposition.up(j);

                    if (g(virtuallevelwritable, blockposition2) || e(virtuallevelwritable, blockposition2)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition2, WorldGenSwampTree.a, structureboundingbox);
                    }
                }

                for (j = blockposition.getY() - 3 + i; j <= blockposition.getY() + i; ++j) {
                    i1 = j - (blockposition.getY() + i);
                    j1 = 2 - i1 / 2;
                    BlockPosition.MutableBlockPosition blockposition_mutableblockposition1 = new BlockPosition.MutableBlockPosition();

                    for (l = blockposition.getX() - j1; l <= blockposition.getX() + j1; ++l) {
                        for (k1 = blockposition.getZ() - j1; k1 <= blockposition.getZ() + j1; ++k1) {
                            blockposition_mutableblockposition1.d(l, j, k1);
                            if (f(virtuallevelwritable, blockposition_mutableblockposition1)) {
                                BlockPosition blockposition3 = blockposition_mutableblockposition1.west();

                                blockposition1 = blockposition_mutableblockposition1.east();
                                BlockPosition blockposition4 = blockposition_mutableblockposition1.north();
                                BlockPosition blockposition5 = blockposition_mutableblockposition1.south();

                                if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition3)) {
                                    this.a(virtuallevelwritable, blockposition3, BlockVine.EAST);
                                }

                                if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition1)) {
                                    this.a(virtuallevelwritable, blockposition1, BlockVine.WEST);
                                }

                                if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition4)) {
                                    this.a(virtuallevelwritable, blockposition4, BlockVine.SOUTH);
                                }

                                if (random.nextInt(4) == 0 && b(virtuallevelwritable, blockposition5)) {
                                    this.a(virtuallevelwritable, blockposition5, BlockVine.NORTH);
                                }
                            }
                        }
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

    private void a(VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, BlockStateBoolean blockstateboolean) {
        IBlockData iblockdata = (IBlockData) Blocks.VINE.getBlockData().set(blockstateboolean, true);

        this.a((IWorldWriter) virtuallevelwritable, blockposition, iblockdata);
        int i = 4;

        for (blockposition = blockposition.down(); b(virtuallevelwritable, blockposition) && i > 0; --i) {
            this.a((IWorldWriter) virtuallevelwritable, blockposition, iblockdata);
            blockposition = blockposition.down();
        }

    }
}
