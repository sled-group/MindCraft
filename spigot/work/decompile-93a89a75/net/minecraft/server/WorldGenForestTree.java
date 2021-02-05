package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenForestTree extends WorldGenTreeAbstract<WorldGenFeatureEmptyConfiguration> {

    private static final IBlockData a = Blocks.DARK_OAK_LOG.getBlockData();
    private static final IBlockData aS = Blocks.DARK_OAK_LEAVES.getBlockData();

    public WorldGenForestTree(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function, boolean flag) {
        super(function, flag);
    }

    @Override
    public boolean a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, Random random, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        int i = random.nextInt(3) + random.nextInt(2) + 6;
        int j = blockposition.getX();
        int k = blockposition.getY();
        int l = blockposition.getZ();

        if (k >= 1 && k + i + 1 < 256) {
            BlockPosition blockposition1 = blockposition.down();

            if (!h(virtuallevelwritable, blockposition1)) {
                return false;
            } else if (!this.a(virtuallevelwritable, blockposition, i)) {
                return false;
            } else {
                this.a(virtuallevelwritable, blockposition1);
                this.a(virtuallevelwritable, blockposition1.east());
                this.a(virtuallevelwritable, blockposition1.south());
                this.a(virtuallevelwritable, blockposition1.south().east());
                EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
                int i1 = i - random.nextInt(4);
                int j1 = 2 - random.nextInt(3);
                int k1 = j;
                int l1 = l;
                int i2 = k + i - 1;

                int j2;
                int k2;

                for (j2 = 0; j2 < i; ++j2) {
                    if (j2 >= i1 && j1 > 0) {
                        k1 += enumdirection.getAdjacentX();
                        l1 += enumdirection.getAdjacentZ();
                        --j1;
                    }

                    k2 = k + j2;
                    BlockPosition blockposition2 = new BlockPosition(k1, k2, l1);

                    if (g(virtuallevelwritable, blockposition2)) {
                        this.a(set, virtuallevelwritable, blockposition2, structureboundingbox);
                        this.a(set, virtuallevelwritable, blockposition2.east(), structureboundingbox);
                        this.a(set, virtuallevelwritable, blockposition2.south(), structureboundingbox);
                        this.a(set, virtuallevelwritable, blockposition2.east().south(), structureboundingbox);
                    }
                }

                for (j2 = -2; j2 <= 0; ++j2) {
                    for (k2 = -2; k2 <= 0; ++k2) {
                        byte b0 = -1;

                        this.a(virtuallevelwritable, k1 + j2, i2 + b0, l1 + k2, structureboundingbox, set);
                        this.a(virtuallevelwritable, 1 + k1 - j2, i2 + b0, l1 + k2, structureboundingbox, set);
                        this.a(virtuallevelwritable, k1 + j2, i2 + b0, 1 + l1 - k2, structureboundingbox, set);
                        this.a(virtuallevelwritable, 1 + k1 - j2, i2 + b0, 1 + l1 - k2, structureboundingbox, set);
                        if ((j2 > -2 || k2 > -1) && (j2 != -1 || k2 != -2)) {
                            byte b1 = 1;

                            this.a(virtuallevelwritable, k1 + j2, i2 + b1, l1 + k2, structureboundingbox, set);
                            this.a(virtuallevelwritable, 1 + k1 - j2, i2 + b1, l1 + k2, structureboundingbox, set);
                            this.a(virtuallevelwritable, k1 + j2, i2 + b1, 1 + l1 - k2, structureboundingbox, set);
                            this.a(virtuallevelwritable, 1 + k1 - j2, i2 + b1, 1 + l1 - k2, structureboundingbox, set);
                        }
                    }
                }

                if (random.nextBoolean()) {
                    this.a(virtuallevelwritable, k1, i2 + 2, l1, structureboundingbox, set);
                    this.a(virtuallevelwritable, k1 + 1, i2 + 2, l1, structureboundingbox, set);
                    this.a(virtuallevelwritable, k1 + 1, i2 + 2, l1 + 1, structureboundingbox, set);
                    this.a(virtuallevelwritable, k1, i2 + 2, l1 + 1, structureboundingbox, set);
                }

                for (j2 = -3; j2 <= 4; ++j2) {
                    for (k2 = -3; k2 <= 4; ++k2) {
                        if ((j2 != -3 || k2 != -3) && (j2 != -3 || k2 != 4) && (j2 != 4 || k2 != -3) && (j2 != 4 || k2 != 4) && (Math.abs(j2) < 3 || Math.abs(k2) < 3)) {
                            this.a(virtuallevelwritable, k1 + j2, i2, l1 + k2, structureboundingbox, set);
                        }
                    }
                }

                for (j2 = -1; j2 <= 2; ++j2) {
                    for (k2 = -1; k2 <= 2; ++k2) {
                        if ((j2 < 0 || j2 > 1 || k2 < 0 || k2 > 1) && random.nextInt(3) <= 0) {
                            int l2 = random.nextInt(3) + 2;

                            int i3;

                            for (i3 = 0; i3 < l2; ++i3) {
                                this.a(set, virtuallevelwritable, new BlockPosition(j + j2, i2 - i3 - 1, l + k2), structureboundingbox);
                            }

                            int j3;

                            for (i3 = -1; i3 <= 1; ++i3) {
                                for (j3 = -1; j3 <= 1; ++j3) {
                                    this.a(virtuallevelwritable, k1 + j2 + i3, i2, l1 + k2 + j3, structureboundingbox, set);
                                }
                            }

                            for (i3 = -2; i3 <= 2; ++i3) {
                                for (j3 = -2; j3 <= 2; ++j3) {
                                    if (Math.abs(i3) != 2 || Math.abs(j3) != 2) {
                                        this.a(virtuallevelwritable, k1 + j2 + i3, i2 - 1, l1 + k2 + j3, structureboundingbox, set);
                                    }
                                }
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    private boolean a(VirtualLevelReadable virtuallevelreadable, BlockPosition blockposition, int i) {
        int j = blockposition.getX();
        int k = blockposition.getY();
        int l = blockposition.getZ();
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

        for (int i1 = 0; i1 <= i + 1; ++i1) {
            byte b0 = 1;

            if (i1 == 0) {
                b0 = 0;
            }

            if (i1 >= i - 1) {
                b0 = 2;
            }

            for (int j1 = -b0; j1 <= b0; ++j1) {
                for (int k1 = -b0; k1 <= b0; ++k1) {
                    if (!a(virtuallevelreadable, (BlockPosition) blockposition_mutableblockposition.d(j + j1, k + i1, l + k1))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void a(Set<BlockPosition> set, VirtualLevelWritable virtuallevelwritable, BlockPosition blockposition, StructureBoundingBox structureboundingbox) {
        if (a((VirtualLevelReadable) virtuallevelwritable, blockposition)) {
            this.a(set, (IWorldWriter) virtuallevelwritable, blockposition, WorldGenForestTree.a, structureboundingbox);
        }

    }

    private void a(VirtualLevelWritable virtuallevelwritable, int i, int j, int k, StructureBoundingBox structureboundingbox, Set<BlockPosition> set) {
        BlockPosition blockposition = new BlockPosition(i, j, k);

        if (b(virtuallevelwritable, blockposition)) {
            this.a(set, (IWorldWriter) virtuallevelwritable, blockposition, WorldGenForestTree.aS, structureboundingbox);
        }

    }
}
