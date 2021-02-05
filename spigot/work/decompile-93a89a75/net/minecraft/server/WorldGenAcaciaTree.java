package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenAcaciaTree extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.ACACIA_LOG.getBlockData();
    private static final IBlockData aS = Blocks.ACACIA_LEAVES.getBlockData();

    public WorldGenAcaciaTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag) {
        super(function, flag);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(3) + random.nextInt(3) + 5;
        boolean flag = true;

        if (blockposition.getY() >= 1 && blockposition.getY() + i + 1 <= 256) {
            int j;
            int k;

            for (int l = blockposition.getY(); l <= blockposition.getY() + 1 + i; ++l) {
                byte b0 = 1;

                if (l == blockposition.getY()) {
                    b0 = 0;
                }

                if (l >= blockposition.getY() + 1 + i - 2) {
                    b0 = 2;
                }

                BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

                for (j = blockposition.getX() - b0; j <= blockposition.getX() + b0 && flag; ++j) {
                    for (k = blockposition.getZ() - b0; k <= blockposition.getZ() + b0 && flag; ++k) {
                        if (l >= 0 && l < 256) {
                            if (!a((VirtualLevelReadable) virtuallevelwritable, (BlockPosition) blockposition_mutableblockposition.d(j, l, k))) {
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
                EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
                int i1 = i - random.nextInt(4) - 1;
                int j1 = 3 - random.nextInt(3);

                j = blockposition.getX();
                k = blockposition.getZ();
                int k1 = 0;

                int l1;

                for (int i2 = 0; i2 < i; ++i2) {
                    l1 = blockposition.getY() + i2;
                    if (i2 >= i1 && j1 > 0) {
                        j += enumdirection.getAdjacentX();
                        k += enumdirection.getAdjacentZ();
                        --j1;
                    }

                    BlockPosition blockposition1 = new BlockPosition(j, l1, k);

                    if (g(virtuallevelwritable, blockposition1)) {
                        this.a(set, (IWorldWriter) virtuallevelwritable, blockposition1, structureboundingbox);
                        k1 = l1;
                    }
                }

                BlockPosition blockposition2 = new BlockPosition(j, k1, k);

                int j2;

                for (l1 = -3; l1 <= 3; ++l1) {
                    for (j2 = -3; j2 <= 3; ++j2) {
                        if (Math.abs(l1) != 3 || Math.abs(j2) != 3) {
                            this.a(set, virtuallevelwritable, blockposition2.b(l1, 0, j2), structureboundingbox);
                        }
                    }
                }

                blockposition2 = blockposition2.up();

                for (l1 = -1; l1 <= 1; ++l1) {
                    for (j2 = -1; j2 <= 1; ++j2) {
                        this.a(set, virtuallevelwritable, blockposition2.b(l1, 0, j2), structureboundingbox);
                    }
                }

                this.a(set, virtuallevelwritable, blockposition2.east(2), structureboundingbox);
                this.a(set, virtuallevelwritable, blockposition2.west(2), structureboundingbox);
                this.a(set, virtuallevelwritable, blockposition2.south(2), structureboundingbox);
                this.a(set, virtuallevelwritable, blockposition2.north(2), structureboundingbox);
                j = blockposition.getX();
                k = blockposition.getZ();
                EnumDirection enumdirection1 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);

                if (enumdirection1 != enumdirection) {
                    l1 = i1 - random.nextInt(2) - 1;
                    j2 = 1 + random.nextInt(3);
                    k1 = 0;

                    int k2;

                    for (int l2 = l1; l2 < i && j2 > 0; --j2) {
                        if (l2 >= 1) {
                            k2 = blockposition.getY() + l2;
                            j += enumdirection1.getAdjacentX();
                            k += enumdirection1.getAdjacentZ();
                            BlockPosition blockposition3 = new BlockPosition(j, k2, k);

                            if (g(virtuallevelwritable, blockposition3)) {
                                this.a(set, (IWorldWriter) virtuallevelwritable, blockposition3, structureboundingbox);
                                k1 = k2;
                            }
                        }

                        ++l2;
                    }

                    if (k1 > 0) {
                        BlockPosition blockposition4 = new BlockPosition(j, k1, k);

                        int i3;

                        for (k2 = -2; k2 <= 2; ++k2) {
                            for (i3 = -2; i3 <= 2; ++i3) {
                                if (Math.abs(k2) != 2 || Math.abs(i3) != 2) {
                                    this.a(set, virtuallevelwritable, blockposition4.b(k2, 0, i3), structureboundingbox);
                                }
                            }
                        }

                        blockposition4 = blockposition4.up();

                        for (k2 = -1; k2 <= 1; ++k2) {
                            for (i3 = -1; i3 <= 1; ++i3) {
                                this.a(set, virtuallevelwritable, blockposition4.b(k2, 0, i3), structureboundingbox);
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

    private void a(Set<BlockPosition> set, IWorldWriter iworldwriter, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        this.a(set, iworldwriter, blockposition, WorldGenAcaciaTree.a, structureboundingbox);
    }

    private void a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        if (g(virtuallevelwritable, blockposition)) {
            this.a(set, (IWorldWriter) virtuallevelwritable, blockposition, WorldGenAcaciaTree.aS, structureboundingbox);
        }

    }
}
